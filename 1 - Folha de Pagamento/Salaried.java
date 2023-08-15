import javax.swing.JOptionPane;
public class Salaried extends CEmployee
{
protected double salary; // Variável do salário do trabalhador assalariado

public Salaried(){ // Construtor da classe Salaried sem parâmetros
this("N/A",0);
}

// Construtor da classe Salaried
public Salaried(String name, double salary2){
super(name); // Chamada super para pegar os atributos da CMidia
setSalary(salary2);  // Constrói a variável nummusicas na classe 
}

// Se o funcionário for do tipo assalariado, retorna o salário
public double getPay(){
return salary;
}

// Devolve para o método getInfo (que prepara a impressão) da classe CEmployee suas informações,
// para finalizar a impressão inicialmente criada em CEmployee.
public String getInfo (){
return super.getInfo() + "Salário: \n" + salary;
}

// Método SET para pegar a variável salário
public void setSalary(double salary2){
salary = salary2;
}  
}