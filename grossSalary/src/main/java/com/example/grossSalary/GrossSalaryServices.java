package com.example.grossSalary;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GrossSalaryServices {
    public HashMap<String, String> calculateSalary(SalaryData salaryData){
        return CalculateSalary.getDetails(salaryData.getDesired_salary(), salaryData.getAllowances()) ;
    }
}
