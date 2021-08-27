package payroll.main.utils;

import payroll.model.employee.Employee;
import payroll.model.employee.Syndicate;
import payroll.model.payments.PaymentData;
import payroll.model.payments.PaymentSchedule;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class EmployeeUtils {

    public static Employee findEmployee(ArrayList<Employee> employees, String id){
        Employee foundEmp = null;
        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                foundEmp = employee;
                break;
            }
        }
        return foundEmp;
    }

    public static PaymentData registerEmployeePayData(Scanner input, PaymentSchedule paySchedule){
        PaymentData paymentData;

        System.out.println("\nVamos cadastrar os dados de pagamento!");
        int bank = GeneralUtils.readInt(input, "Digite o número do banco:");
        int agency = GeneralUtils.readInt(input, "Digite o número da agência:");
        int account = GeneralUtils.readInt(input, "Digite o número da conta:");

        String payMethod = GeneralUtils.readPayMethod(input);
        paymentData = new PaymentData(bank, agency, account, payMethod, paySchedule);
        return paymentData;
    }

    public static Syndicate registerEmployeeSyndicate(Scanner input, UUID id){
        Syndicate syndicate = null;
        int member = GeneralUtils.readInt(input, "\nO empregado é membro do sindicato? ([1] - Sim, [2] - Não): ");
        if (member == 1){
            UUID syndicateId = UUID.randomUUID();
            Double tax = GeneralUtils.readDouble(input, "Digite a taxa sindical:");
            syndicate = new Syndicate(syndicateId, id, true, tax);
        }
        return syndicate;
    }

}
