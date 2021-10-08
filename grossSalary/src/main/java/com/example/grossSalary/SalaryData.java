package com.example.grossSalary;

import java.util.HashMap;

public class SalaryData {
    private double desired_salary;
    private HashMap<String, Double> allowances = new HashMap<String, Double>();

    public SalaryData(double desired_salary, HashMap<String, Double> allowances) {
        this.desired_salary = desired_salary;
        this.allowances = allowances;
    }

    public double getDesired_salary() {
        return desired_salary;
    }

    public HashMap<String, Double> getAllowances() {
        return allowances;
    }
}
