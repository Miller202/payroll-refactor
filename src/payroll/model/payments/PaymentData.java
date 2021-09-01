package payroll.model.payments;

import payroll.model.employee.Employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentData implements Serializable {

    private int bank;

    private int agency;

    private int account;

    private String paymentMethod;

    private PaymentSchedule schedule;

    private ArrayList<PayCheck> payChecks;


    public PaymentData(int bank, int agency, int account, String paymentMethod, PaymentSchedule schedule) {
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.paymentMethod = paymentMethod;
        this.schedule = schedule;
        this.payChecks = new ArrayList<PayCheck>();
    }

    public int getBank() {
        return bank;
    }

    public int getAgency() {
        return agency;
    }

    public int getAccount() {
        return account;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public ArrayList<PayCheck> getPayChecks() {
        return payChecks;
    }

    @Override
    public String toString() {
        return " {" +
                "Banco: " + getBank() +
                ", Agencia: " + getAgency() +
                ", Conta: " + getAccount() +
                ", Método de pagamento: '" + getPaymentMethod() + '\'' +
                ", \nAgenda: '" + getSchedule().toString() + '\'' +
                '}';
    }

    public boolean verifyPayDate(int week, LocalDate current){
        boolean alreadyPay = false;
        boolean dateInSchedule;

        dateInSchedule = this.getSchedule().isDateInSchedule(week, current);

        for(PayCheck pc : this.getPayChecks()){
            if (pc.getDate() == current) {
                alreadyPay = true;
                break;
            }
        }

        return (dateInSchedule && (!alreadyPay));
    }
}
