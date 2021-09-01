package payroll.control.interpreter;

import payroll.model.employee.Commissioned;
import payroll.model.employee.Employee;

public class CommissionedFilter implements EmployeeFilterInterpreter{
    @Override
    public boolean instanceEmployee(Employee emp) {
        return emp instanceof Commissioned;
    }
}
