package payroll.control.strategy;

import payroll.model.payments.PaymentSchedule;

import java.time.LocalDate;

public interface ScheduleStrategy {
    public int getMethodDiv();

    public boolean getDateInSchedule(PaymentSchedule empSchedule, int week, LocalDate current);
}
