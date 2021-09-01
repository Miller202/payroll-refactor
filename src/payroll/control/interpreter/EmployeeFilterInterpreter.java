package payroll.control.interpreter;

import payroll.model.employee.Employee;

public interface EmployeeFilterInterpreter {

    boolean instanceEmployee(Employee emp);

}
