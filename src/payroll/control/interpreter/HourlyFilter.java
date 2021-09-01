package payroll.control.interpreter;

import payroll.model.employee.Employee;
import payroll.model.employee.Hourly;

public class HourlyFilter implements EmployeeFilterInterpreter{
    @Override
    public boolean instanceEmployee(Employee emp) {
        return emp instanceof Hourly;
    }
}
