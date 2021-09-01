package payroll.control.strategy;

import payroll.model.payments.PaymentSchedule;

import java.time.LocalDate;

public interface ScheduleStrategy {
    int getMethodDiv();

    boolean getDateInSchedule(PaymentSchedule paySchedule, int week, LocalDate current);
}
