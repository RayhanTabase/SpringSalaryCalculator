# SpringSalaryCalculator
Springboot app to calculate salary and breakdown using GRA and 3 tier pension data as well as desired net salary

 ### Example of api structure, send an api POST request with the following fields/keys in the body(desired_salary and allowances)
 ## path to api = "localhost:8080/" + "api/grossSalary" ie localhost:8080/api/grossSalary
 ## body of request = 
{
    "desired_salary":amount,
    "allowances":{
        "allowance Name":amount,
        "allowance Name":amount
    }
 }
 
 the allowances can take multiple values  

 {
    "desired_salary":2000,
    "allowances":{
        "rent":1000,
        "utility":500,
        "transport":700
    }
 }
 
 
 ### The response is a json  with the keys gross_salary, basic_salary, total_tax_due, employee_pension_amount, employer_pension_amount:
   {
    "gross_salary": "3026.46",
    "basic_salary": "2826.46",
    "total_tax_due": "17.00",
    "employee_pension_amount": "309.46",
    "employer_pension_amount": "536.44"
}
 
  ###  See src/main/package com.example.grossSalary for api code
  ### See src/test for unit tests
  
  

A young woman about to negotiate her job offer at an agricultural engineering company is expected to discuss with the company in terms of gross salary, however she is interested more in what hits her bank account (net). Assist her with a REST API that she can send her desired net salary and allowances to such that the API will return the corresponding gross salary and additional details (Basic Salary, Total PAYE Tax, Employee Pension Contribution Amount and Employer Pension amount).
The company pays 3 tier pensions as below
Pension Tier         Employee Rate (%).    Employer Rate (%)
Tier 1                    0                                  13
Tier 2                    5.5                                0
Tier 3                    5                                   5
Here are some additional relevant rules:
1.	Allowances are added to basic salary for taxation
2.	Employee Pension contributions are taken out of taxable income before being taxed
3.	PAYE tax is per the current graduated income tax rates of the GRA (Attached below)
4.	Gross salary is made of basic salary and total allowance amounts


![alt text](https://github.com/RayhanTabase/SpringSalaryCalculator/blob/main/thumbnail.png?raw=true)
 
 
 
 
 
 
 
