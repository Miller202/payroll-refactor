package payroll.control.interpreter;

import payroll.model.employee.Employee;

public class SyndicateFilter implements EmployeeFilterInterpreter{
    @Override
    public boolean instanceEmployee(Employee emp) {
        return (emp.getSyndicate() != null && emp.getSyndicate().isActive());
    }
}
