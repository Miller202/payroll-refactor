package payroll.model;

import payroll.control.PaymentsControl;
import payroll.control.interpreter.EmployeeFilterInterpreter;
import payroll.model.employee.Employee;
import payroll.model.payments.PaymentList;
import payroll.model.payments.PaymentSchedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Enterprise implements Serializable {

    private ArrayList<Employee> employees;

    private final ArrayList<PaymentList> paymentsLists;

    private ArrayList<PaymentSchedule> paymentSchedules;


    public Enterprise(){
        this.employees = new ArrayList<>();
        this.paymentsLists = new ArrayList<>();
        this.paymentSchedules = PaymentsControl.startSchedules();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Employee> getFilteredEmployees(EmployeeFilterInterpreter interpreter){
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (interpreter.instanceEmployee(employee)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public ArrayList<PaymentList> getPaymentsLists() {
        return paymentsLists;
    }

    public ArrayList<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

}
