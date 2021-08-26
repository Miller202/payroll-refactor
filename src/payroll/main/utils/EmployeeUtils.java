package payroll.main.utils;

import payroll.model.employee.Employee;

import java.util.ArrayList;

public class EmployeeUtils {
    public static Employee findEmployee(ArrayList<Employee> employees, String id){
        Employee foundEmp = null;
        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                foundEmp = employee;
                break;
            }
        }
        return foundEmp;
    }
}
