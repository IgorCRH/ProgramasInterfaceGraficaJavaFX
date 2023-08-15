public class CEmployee implements Employee // Classe CEmployee implementa a interface Employee
{
protected String name; // name do Funcionário

// Construtor da Classe
public CEmployee(String name2){
this.name = name2;
}

// Métodos SET para pegar a variável name
public void setName(String name2){
name = name2;
}

// Método GET para retornar a variável name
@Override
public String getName(){
return name;
}

// Método para construir a classe sem parâmetros, para definir o tipo de funcionário
public CEmployee (){
this("N/A");
}

// Método que prepara o retorno de qual tipo será retornado para a impressão.
public double getPay(){
throw new UnsupportedOperationException("Método não implementado nesta classe");
}

// Método que prepara o retorno da impressão dos detalhes de cada produto.
public String getInfo(){
return "name: \n" +getName() + "\n";
}

// Método que prepara a impressão dos detalhes de todos os produtos das classes,
// juntando as informações inicialmente preparadas dos métodos GetInfo e Tipo.
public void printPay(){
System.out.println("Informações: \n");
String print = getInfo() + getPay();
System.out.println(print);
}
}