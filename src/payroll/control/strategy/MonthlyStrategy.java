package payroll.control.strategy;

import payroll.main.utils.GeneralUtils;
import payroll.model.payments.PaymentSchedule;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MonthlyStrategy implements ScheduleStrategy, Serializable {
    @Override
    public int getMethodDiv() {
        return 1;
    }

    @Override
    public boolean getDateInSchedule(PaymentSchedule empSchedule, int week, LocalDate current) {
        if (empSchedule.getMonthDay() == null) {
            return current.isEqual(GeneralUtils.
                    getLastJobDay(current.with(TemporalAdjusters.lastDayOfMonth())));
        } else {
            return (empSchedule.getMonthDay() == current.getDayOfMonth());
        }
    }
}
