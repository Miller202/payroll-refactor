package payroll.model.services;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleResult implements Serializable {

    public Double value;

    public LocalDate date;


    public SaleResult(Double value, LocalDate date) {
        this.value = value;
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return " Resultado da venda {" +
                "valor da venda: " + getValue() +
                ", data: " + getDate() +
                '}' + "\n";
    }
}
