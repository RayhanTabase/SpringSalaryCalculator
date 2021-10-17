package com.example.grossSalary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GrossSalaryServicesTest {

    @Autowired
    private GrossSalaryServices grossSalaryServices;

    @Test
    void test_SalaryData(){
        double desired_salary = 20_000;
        HashMap<String, Double> allowances = new HashMap<String, Double>() ;
        allowances.put("rent",10_000d);
        allowances.put("transport",5_000d);
        SalaryData salaryData = new SalaryData(desired_salary,allowances);
        assertEquals(salaryData.getDesired_salary(),desired_salary);
        assertEquals(salaryData.getAllowances(),allowances);
    }

    @Test
    void test_zeroTax(){
        double desired_salary = 418;
        HashMap<String, Double> allowances = new HashMap<String, Double>();
        SalaryData salaryData = new SalaryData(desired_salary,allowances);
        HashMap<String, String> data = grossSalaryServices.calculateSalary(salaryData);
        String total_tax = data.get ("total_tax_due");
        assertEquals(Double.parseDouble(total_tax),0);
    }

    @Test
    void cumulative_5(){
        double desired_salary = 419;
        HashMap<String, Double> allowances = new HashMap<String, Double>();
        SalaryData salaryData = new SalaryData(desired_salary,allowances);
        HashMap<String, String> data = grossSalaryServices.calculateSalary(salaryData);
        String total_tax = data.get ("total_tax_due");
        assertEquals(Double.parseDouble(total_tax),5);

    }

    @Test
    void cumulative_4657_25(){
        double desired_salary = 20_000;
        HashMap<String, Double> allowances = new HashMap<String, Double>();
        SalaryData salaryData = new SalaryData(desired_salary,allowances);
        HashMap<String, String> data = grossSalaryServices.calculateSalary(salaryData);
        String total_tax = data.get ("total_tax_due");
        assertEquals(Double.parseDouble(total_tax),4657.25);

    }

    @Test
    void cumulative_exceed(){
        double desired_salary = 30_000;
        HashMap<String, Double> allowances = new HashMap<String, Double>();
        SalaryData salaryData = new SalaryData(desired_salary,allowances);
        HashMap<String, String> data = grossSalaryServices.calculateSalary(salaryData);
        String total_tax = data.get ("total_tax_due");
        assertTrue(Double.parseDouble(total_tax) > 4657.25);
    }

    @Test
    void test_calculation(){
        double desired_salary = 20_000;
        HashMap<String, Double> allowances = new HashMap<String, Double>() ;
        SalaryData newSalary = new SalaryData(desired_salary,allowances);

        HashMap<String, String> data = grossSalaryServices.calculateSalary(newSalary);
        String gross_salary = data.get("gross_salary");
        String basic_salary = data.get("basic_salary");
        String total_tax = data.get ("total_tax_due");
        String employee_pension = data.get("employee_pension_amount");
        String employer_pension = data.get("employer_pension_amount");
        double check_desired_salary = Double.parseDouble(basic_salary) - (Double.parseDouble(employee_pension) + Double.parseDouble(total_tax) ) ;

        assertEquals(check_desired_salary,desired_salary);
    }

    @Test
    void test_pension(){
        double desired_salary = 20_000;
        HashMap<String, Double> allowances = new HashMap<String, Double>() ;
        SalaryData newSalary = new SalaryData(desired_salary,allowances);

        HashMap<String, String> data = grossSalaryServices.calculateSalary(newSalary);
        String employee_pension = data.get("employee_pension_amount");
        String employer_pension = data.get("employer_pension_amount");
        assertTrue(Double.parseDouble(employer_pension) > Double.parseDouble(employee_pension));
    }
}