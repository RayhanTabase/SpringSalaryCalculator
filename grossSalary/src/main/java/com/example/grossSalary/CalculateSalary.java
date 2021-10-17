package com.example.grossSalary;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CalculateSalary {
    public static HashMap<String, String> getDetails(double desired_income, HashMap<String, Double> allowances){

        DecimalFormat df = new DecimalFormat("#.00");

        HashMap<String, String> error = new HashMap<String, String>();

        if(desired_income< 0){
            System.out.println("Invalid amount");
            error.put("Error", "Invalid desired income amount, must be positive");
            return error;
        }

        double total_allowances = 0;
        for (double i : allowances.values()) {
             System.out.println(i);
            if(i < 0){
                error.put("Error", "Invalid allowance amount, must be positive");
                return error;
            }
            total_allowances += i;
        }

        double total_net = desired_income + total_allowances;
        double[] x = paye(total_net);
        double taxable_income = x[0];
        String total_tax_payable =  df.format(x[1]);
        double[] y = pension_contributions(taxable_income);
        String amount_before_pension = df.format(y[0]);
        String employee_pension = df.format(y[1]);
        String employer_pension = df.format(y[2]);
        double basic_salary =  y[0] - total_allowances;
        String bs = df.format(basic_salary);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("gross_salary", amount_before_pension);
        data.put("total_allowances", df.format(total_allowances));
        data.put("basic_salary", bs);
        data.put("total_tax_due", total_tax_payable);
        data.put("employee_pension_amount", employee_pension);
        data.put("employer_pension_amount", employer_pension);
        return data;
    }

    public static double[] pension_contributions(double taxable_income) {
        float[] employee_rate = {0f,0.055f,0.05f};
        float[] employer_rate = {0.13f,0.0f,0.05f};

        double amount_before_pension = taxable_income;
        float total_employer_amt = 0;
        float total_employee_amt = 0;

        for(var i = 2; i>= 0; i--){
            amount_before_pension = amount_before_pension / (1-employee_rate[i]) ;
            total_employee_amt += amount_before_pension * employee_rate[i] ;
            total_employer_amt += amount_before_pension * employer_rate[i] ;
        }

        double[] result = {amount_before_pension,total_employee_amt,total_employer_amt};
        return result;
    }

    public static double[] paye(double total_net_income){
        // deductible above 20,000 30% = total_net - 6_000 / 0.7

        // int[] chargeable_income = {319,100,120,3_000,16_461,20_000} ;
        int[] cumulative_chargeable = {319,419,539,3539,20_000} ;
        float[] cumulative_tax = {0,5,17,542,4_657.25f} ;
        float[] tax_rates = {0f, 0.05f, 0.10f, 0.175f, 0.25f, 0.30f} ;

        double taxable_income = total_net_income; //if amount is less than 419, amount is returned
        double total_tax_payable = 0d;

        double EXCEED_TAX = 10_657.25;
        double HIGHEST_AMOUNT_CAP = 40_000.01;

        if(total_net_income >= cumulative_chargeable[1]){

            if(total_net_income + EXCEED_TAX >= HIGHEST_AMOUNT_CAP){
                taxable_income = (total_net_income - 1_342.75) / 0.7;
                total_tax_payable = cumulative_tax[4] + (tax_rates[5] * (taxable_income - cumulative_chargeable[4])) ;
            }

            else{
                int i = 4;
                while(i > 0){
                    if(total_net_income + cumulative_tax[i] >= cumulative_chargeable[i]){
                        taxable_income = total_net_income + cumulative_tax[i];
                        total_tax_payable = cumulative_tax[i];
                        break;
                    }
                    i--;
                }
            }
        }
        double[] result = {taxable_income, total_tax_payable};
        return result;
    }
}
