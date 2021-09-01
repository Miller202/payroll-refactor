package payroll.model.payments;

import payroll.control.strategy.ScheduleStrategy;
import payroll.model.employee.Employee;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class PaymentSchedule implements Serializable {

    private Integer monthDay;

    private DayOfWeek weekDay;

    private String schedule;

    private ScheduleStrategy strategy;


    public PaymentSchedule(Integer monthDay, DayOfWeek weekDay, String schedule, ScheduleStrategy strategy) {
        this.monthDay = monthDay;
        this.weekDay = weekDay;
        this.schedule = schedule;
        this.strategy = strategy;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public String getSchedule() {
        return schedule;
    }

    public ScheduleStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return " Agenda de pagamento {" +
                "Dia do mês: " + getMonthDay() +
                ", Dia da semana: " + getWeekDay() +
                ", Tipo de agenda: '" + getSchedule() + '\'' +
                '}';
    }

    public int calcMethodDiv(){
        return this.getStrategy().getMethodDiv();
    }

    public boolean isDateInSchedule(int week, LocalDate current){
        return this.getStrategy().getDateInSchedule(this, week, current);
    }
}
