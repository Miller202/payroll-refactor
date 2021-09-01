package payroll.control.strategy;

import payroll.model.payments.PaymentSchedule;

import java.io.Serializable;
import java.time.LocalDate;

public class BiWeeklyStrategy implements ScheduleStrategy, Serializable {
    @Override
    public int getMethodDiv() {
        return 2;
    }

    @Override
    public boolean getDateInSchedule(PaymentSchedule paySchedule, int week, LocalDate current) {
        return (paySchedule.getWeekDay() == current.getDayOfWeek() && week%2==0);
    }
}
