# Projeto OO: Sistema de Folha de Pagamento

<p align="justify"> O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e impostos deduzidos do salário.
</p>

* <p align="justify">Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal durante as horas extras. Eles são pagos toda sexta-feira.</p>

* <p align="justify">Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês (desconsidere feriados). Tais empregados são chamados "assalariados".</p>

* <p align="justify">Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um percentual das vendas que realizam. Eles submetem resultados de vendas que informam a data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas de salário fixo mais as comissões do período.
  
  - Empregados podem escolher o método de pagamento.
  - Podem receber um cheque pelos correios
  - Podem receber um cheque em mãos
  - Podem pedir depósito em conta bancária

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
