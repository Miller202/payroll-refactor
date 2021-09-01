package payroll.control;

import payroll.main.utils.GeneralUtils;
import payroll.model.employee.Commissioned;
import payroll.model.employee.Employee;
import payroll.model.employee.Hourly;
import payroll.model.services.SaleResult;
import payroll.model.services.ServiceTax;
import payroll.model.services.TimeCard;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicesControl {

    public static void postTimeCard(Scanner input, ArrayList<Employee> Employees){
        System.out.println("Digite o ID do empregado:");
        String id = input.nextLine();

        Hourly employeeToPost = null;
        for(Employee hourly : Employees){
            if(hourly.getId().toString().equals(id)){
                employeeToPost = (Hourly) hourly;
            }
        }

        if(employeeToPost == null){
            System.out.println("Empregado não encontrado na lista de Horistas!");
        }else{
            LocalDate date = GeneralUtils.readData(input);

            System.out.println("Horário de entrada:");
            LocalTime timeEntry = GeneralUtils.readTime(input);
            System.out.println("Horário de saída:");
            LocalTime timeOut = GeneralUtils.readTime(input);

            TimeCard timeCard = new TimeCard(date, timeEntry, timeOut);
            employeeToPost.getTimeCards().add(timeCard);
            System.out.println("Cartão de ponto registrado!");
        }

    }

    public static void postSaleResult(Scanner input, ArrayList<Employee> Employees){
        System.out.println("Digite o ID do empregado:");
        String id = input.nextLine();

        Commissioned employeeToPost = null;
        for(Employee commissioned : Employees){
            if(commissioned.getId().toString().equals(id)){
                employeeToPost = (Commissioned) commissioned;
            }
        }

        if(employeeToPost == null){
            System.out.println("Empregado não encontrado na lista de Comissionados!");
        }else{
            System.out.println("Digite o valor da venda:");
            Double value = input.nextDouble();

            LocalDate date = GeneralUtils.readData(input);

            SaleResult saleResult =  new SaleResult(value, date);
            employeeToPost.getSaleResults().add(saleResult);
            System.out.println("Resultado da venda registrado!");
        }
    }

    public static void postServiceTax(Scanner input, ArrayList<Employee> Employees){
        System.out.println("Digite o ID do empregado:");
        String id = input.nextLine();

        Employee employeeToPost = null;
        for(Employee employee : Employees){
            if(employee.getId().toString().equals(id)){
                employeeToPost = employee;
            }
        }

        if(employeeToPost == null){
            System.out.println("Empregado não encontrado na lista de membros do sindicato!");
        }else{
            System.out.println("Digite o valor da taxa de serviço:");
            Double value = input.nextDouble();

            LocalDate date = GeneralUtils.readData(input);

            ServiceTax serviceTax = new ServiceTax(value, date);
            employeeToPost.getSyndicate().getServiceTaxes().add(serviceTax);
        }
    }
}
