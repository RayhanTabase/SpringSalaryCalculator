package com.example.grossSalary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping(path="api/grossSalary")
public class GrossSalaryController {

    private  final GrossSalaryServices grossSalaryServices;

    @Autowired
    public GrossSalaryController(GrossSalaryServices grossSalaryServices) {
        this.grossSalaryServices = grossSalaryServices;
    }

    @PostMapping
    public HashMap<String, String> addSalary(@RequestBody SalaryData salaryData){
        return grossSalaryServices.calculateSalary(salaryData);
    }
}
