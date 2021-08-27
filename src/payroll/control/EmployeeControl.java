package payroll.control;

import payroll.main.utils.EmployeeUtils;
import payroll.main.utils.GeneralUtils;
import payroll.model.employee.*;
import payroll.model.payments.PaymentData;
import payroll.model.payments.PaymentSchedule;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class EmployeeControl {

    public static Employee register(Scanner input, ArrayList<PaymentSchedule> paymentSchedules){
        Employee employee;
        Syndicate syndicate;
        PaymentData paymentData;
        PaymentSchedule paySchedule;
        int answer;

        UUID id = UUID.randomUUID();
        String name = GeneralUtils.readString(input, "\nDigite o nome do empregado:");
        String address = GeneralUtils.readString(input, "\nDigite o endereço:");

        System.out.println("\nQual o tipo de empregado?");
        answer = GeneralUtils.readInt(input, "[1] - Horista, [2] - Salariado, [3] - Comissionado");

        if(answer == 1){
            Double hourlySalary = GeneralUtils.readDouble(input, "Digite o salário por hora:");
            employee = new Hourly(id, name, address, null, null, hourlySalary);
            paySchedule = paymentSchedules.get(1);
        }
        else if(answer == 2){
            Double salary = GeneralUtils.readDouble(input, "Digite o salário:");
            employee = new Salaried(id, name, address, null, null, salary);
            paySchedule = paymentSchedules.get(0);
        }
        else if(answer == 3){
            Double fixedSalary = GeneralUtils.readDouble(input, "Digite o salário fixo:");
            Double commission = GeneralUtils.readDouble(input, "Digite a taxa de comissão:");
            employee = new Commissioned(id, name, address, null, null, fixedSalary, commission);
            paySchedule = paymentSchedules.get(2);
        }else{
            Double salary = GeneralUtils.readDouble(input, "Digite o salário:");
            employee = new Salaried(id, name, address, null, null, salary);
            paySchedule = paymentSchedules.get(0);
        }

        syndicate = EmployeeUtils.registerEmployeeSyndicate(input, id);
        employee.setSyndicate(syndicate);

        input.nextLine();

        paymentData = EmployeeUtils.registerEmployeePayData(input, paySchedule);
        employee.setPaymentData(paymentData);

        input.nextLine();
        System.out.println();
        System.out.println("Empregado cadastrado com sucesso!");
        System.out.println(employee.toString());

        return employee;
    }

    public static void removeEmployee(Scanner input, ArrayList<Employee> employees){
        String id = GeneralUtils.readString(input, "Digite o ID do empregado que deve ser removido:");

        Employee employeeToRemove = EmployeeUtils.findEmployee(employees, id);
        employees.remove(employeeToRemove);

        if(employeeToRemove == null){
            System.out.println("Empregado não foi encontrado!");
        }else{
            System.out.println("Empregado removido com sucesso!");
        }
    }

    public static void listEmployees(ArrayList<Employee> employees) {
        int i = 1;
        System.out.println("\n\nListagem de empregados");
        for (Employee employee : employees) {
            System.out.println("\nEmpregado #" + i);
            System.out.println(employee.toString());
            System.out.println("\n");
            i++;
        }
    }

    public static void editEmployee(Scanner input, ArrayList<Employee> employees){

        String id = GeneralUtils.readString(input, "\nDigite o ID do empregado:");

        Employee employeeToEdit = EmployeeUtils.findEmployee(employees, id);

        if(employeeToEdit == null){
            System.out.println("\nEmpregado não foi encontrado!");
        }
        else{
            System.out.println("\nQual informação deseja editar?");
            System.out.println("[1] Nome");
            System.out.println("[2] Endereço");
            System.out.println("[3] Tipo de empregado");
            System.out.println("[4] Método de pagamento");
            System.out.println("[5] Vínculo ao sindicato");
            System.out.println("[6] Taxa do sindicato");
            int option = input.nextInt();
            input.nextLine();

            if(option == 1){
                String name = GeneralUtils.readString(input, "Digite o novo nome: ");
                employeeToEdit.setName(name);
                System.out.println("Nome editado!");
            }
            else if(option == 2){
                String address = GeneralUtils.readString(input, "Digite o novo endereço: ");
                employeeToEdit.setAddress(address);
                System.out.println("Endereço editado!");
            }
            else if(option == 3){
                System.out.println("\nEscolha o novo tipo");
                int type = GeneralUtils.readInt(input, "[1] - Horista, [2] - Salariado, [3] - Comissionado");

                Employee newEmployee = null;
                if(type == 1){
                    Double hourlySalary = GeneralUtils.readDouble(input, "Digite o salário por hora:");
                    System.out.println();

                    newEmployee = new Hourly(employeeToEdit.getId(), employeeToEdit.getName(),
                            employeeToEdit.getAddress(), employeeToEdit.getSyndicate(),
                            employeeToEdit.getPaymentData(), hourlySalary);
                }else if(type == 2){
                    Double salary = GeneralUtils.readDouble(input, "Digite o salário:");
                    System.out.println();

                    newEmployee = new Salaried(employeeToEdit.getId(), employeeToEdit.getName(),
                            employeeToEdit.getAddress(), employeeToEdit.getSyndicate(),
                            employeeToEdit.getPaymentData(), salary);
                }else if(type == 3){
                    Double fixedSalary = GeneralUtils.readDouble(input, "Digite o salário fixo:");
                    System.out.println();
                    Double commission = GeneralUtils.readDouble(input, "Digite a taxa de comissão:");
                    System.out.println();

                    newEmployee = new Commissioned(employeeToEdit.getId(), employeeToEdit.getName(),
                            employeeToEdit.getAddress(), employeeToEdit.getSyndicate(),
                            employeeToEdit.getPaymentData(), fixedSalary, commission);
                }else{
                    System.out.println("Opção inválida!");
                }

                employees.remove(employeeToEdit);
                employees.add(newEmployee);
                System.out.println("\nTipo de empregado editado com sucesso!");
            }
            else if(option == 4){
                String payMethod = GeneralUtils.readPayMethod(input);
                employeeToEdit.getPaymentData().setPaymentMethod(payMethod);
                System.out.println("Método de pagamento atualizado!");
            }
            else if(option == 5){
                if(employeeToEdit.getSyndicate() == null){
                    System.out.println("Empregado não pertence ao sindicato, deseja cadastrar?");
                    int choice = GeneralUtils.readInt(input, "[1] Sim, [2] Não");
                    if(choice == 1){
                        Double tax = GeneralUtils.readDouble(input, "Digite a taxa sindical:");
                        employeeToEdit.setSyndicate(new Syndicate(UUID.randomUUID(), employeeToEdit.getId(), true, tax));
                    }
                }else{
                    if(employeeToEdit.getSyndicate().getActive()){
                        System.out.println("Seu cadastro no sindicato está ativo, deseja desativar?");
                        int choice = GeneralUtils.readInt(input, "[1] Sim, [2] Não");
                        if(choice == 1){
                            employeeToEdit.getSyndicate().setActive(false);
                        }
                    }else{
                        System.out.println("Seu cadastro no sindicato está desativado, deseja ativar?");
                        int choice = GeneralUtils.readInt(input, "[1] Sim, [2] Não");
                        if(choice == 1){
                            employeeToEdit.getSyndicate().setActive(true);
                        }
                    }
                }
                System.out.println("Operação realizada com sucesso!");
            }
            else if(option == 6){
                if(employeeToEdit.getSyndicate() == null){
                    System.out.println("Empregado não pertence ao sindicato");
                }else{
                    Double tax = GeneralUtils.readDouble(input, "Digite a nova taxa sindical:");
                    employeeToEdit.getSyndicate().setTax(tax);
                }
                System.out.println("Operação realizada com sucesso!");
            }
            else{
                System.out.println("Opção inválida!");
            }
        }

    }

    public static void editEmployeeSchedule(Scanner input, ArrayList<Employee> employees,
                                            ArrayList<PaymentSchedule> paymentSchedules){

        String id = GeneralUtils.readString(input, "\nDigite o ID do empregado:");

        boolean foundEmp = false;
        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                foundEmp = true;
                int counter = 0;

                StringBuilder str = new StringBuilder("\n-Escolha uma das agendas para receber seu salário-\n");
                for(PaymentSchedule p : paymentSchedules){
                    str.append('[').append(counter).append(']').append(p.toString()).append('\n');
                    counter +=1;
                }
                System.out.println(str);

                int choice = input.nextInt();

                if(choice <= counter && choice >= 0){
                    employee.getPaymentData().setSchedule(paymentSchedules.get(choice));
                    System.out.println("Agenda atualizada!\n");
                }else{
                    System.out.println("Opção inválida");
                }

            }
        }

        if(!foundEmp){
            System.out.println("\nEmpregado não foi encontrado!");
        }

    }
}
