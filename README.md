# Projeto OO: Sistema de Folha de Pagamento

<p align="justify"> O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e impostos deduzidos do salário.
</p>

* <p align="justify">Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal durante as horas extras. Eles são pagos toda sexta-feira.</p>

* <p align="justify">Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês (desconsidere feriados). Tais empregados são chamados "assalariados".</p>

* <p align="justify">Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um percentual das vendas que realizam. Eles submetem resultados de vendas que informam a data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas de salário fixo mais as comissões do período.

</p>

* <p align="justify">Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato). O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo contracheque do empregado. A identificação do empregado no sindicato não é a mesma da identificação no sistema de folha de pagamento.</p>

* <p align="justify">A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o pagamento para cada empregado desde a última vez em que este foi pago.</p>

## Code Smells

* Duplicate code
  - Repetição dos loops (condicionais) para encontrar um empregado em (EditEmployee) e (RemoveEmployee);
  - Classes ServiceTax e SaleReport possuem exatamente os mesmos atributos: date (LocalDate) e value (Double); além disso, contam com o mesmo método toString();
 
* Long method
  - Declarações de if/else muito extensas no menu principal e no menu para editar empregado;
  - Métodos extensos que acumulam muitas variáveis locais;
  - Métodos que acumulam decisões lógicas (toStrings).

* Feature Envy
  - O método getMethodDiv() está mais relacionado à classe PaymentsControl, que trata do controle e realização dos pagamentos, de acordo com as formas de pagamento e agendas.

* Generative speculation
  - Construtores que nunca foram utilizados;
  - Métodos getters e setter que não são utilizados;
  - Método readSchedule (classe GeneralUtils) não é utilizado.
  - String schedule no método register(classe EmployeeMenu) não é utilizada.

## Refactoring

* Interpreter
  - Foi aplicado o Design Pattern Interpreter para solucionar um code smell da classe ServicesControl, nas filtragens de empregados para lançar cartão de ponto, resultados de venda e taxas de serviço. Essas filtragens estavam sendo repetidas várias vezes na classe, então foi definida uma interface com o método abstrato para decidir se um empregado deve ser filtrado ou não. Foi criado um método para criar a lista de empregados filtrados com base no interpretador, resolvendo assim, o code smell. [Veja a solução aqui](https://github.com/Miller202/payroll-refactor/commit/f576ee41b55e1cf9be224e7b41bf2faa375b52d8)

* Strategy
  - Foi aplicado o Design Pattern Strategy para solucionar um code smell da classe PaymentsControl, nos métodos getMethodDiv() e VerifyPayDate(), definindo uma interface com os métodos abstratos criando uma classe concreta para cada tipo de agenda, com as implementações do comportamento adequado para cada uma delas. Na classe PaymentSchedule, foi adicionado um atributo strategy, além disso foi adicionado um novo método nessa interface, de forma a modularizar o código (método getDateInSchedule). [Veja a solução aqui](https://github.com/Miller202/payroll-refactor/commit/f23c1823533c0860b0556770fd252472b77fcb0f)

* Move Accumulation to Collecting Parameter
  - Diversos métodos extensos foram refatorados para aumentar a modularização;
  - Métodos da classe EmployeeControl foram modularizados, com a remoção de duplicated code nos loops, [veja a solução aqui](https://github.com/Miller202/payroll-refactor/commit/2659a848dc2c57861983c26630ea4be9fa233485);
  - Modularização com a criação de métodos utils para ler dados nos métodos register e editEmployee da classe EmployeeControl, [veja a solução aqui](https://github.com/Miller202/payroll-refactor/commit/d5b6dc1da9fbc550de000ce8d376c35708bd038e);
  - Simplificação do toString das classes Employee e PayCheck, [veja a solução aqui](https://github.com/Miller202/payroll-refactor/commit/e4591646abe5943794102eb297fcf4a863715971).

* Remove Generative Speculation
  - Alguns métodos construtores, getters e setters nunca foram utilizados. Logo, foram removidos na refatoração, [veja aqui](https://github.com/Miller202/payroll-refactor/commit/9d7ff5cc64dd246539fd2c2d1785f45f62a6e554). O mesmo aconteceu com o método readSchedule da classe GeneralUtils e a variável schedule no register employee. [veja a solução aqui](https://github.com/Miller202/payroll-refactor/commit/9839794051c5bd519d26d6df061e29cad1a28935)

* Move method
  - O método calcMethodDiv() foi movido para a classe PaymentSchedule, pois trata justamente do controle das agendas de pagamento;
  - O método verifyPayDate() foi movido para a classe PaymentData, pois lida com os paychecks (comprovantes de pagamento) e as agendas.
  - Essas alterações podem ser visualizadas nesse commit: [veja aqui](https://github.com/Miller202/payroll-refactor/commit/cedb51a88df3225a7f0bb622af2dcc5af181d67e).
