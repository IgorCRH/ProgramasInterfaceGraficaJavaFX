public class Hourly extends CEmployee
{
protected double hourlyrate;
protected double hours; // Variáveis da classe do trabalhador por horas

public Hourly(){ // Construtor da classe Hourly sem parâmetros
this("N/A",0,0);
}

// Construtor da classe Hourly
public Hourly(String name, double hourlyrate2, double hours2){
super(name); // Chamada super para pegar os atributos da CMidia
setHourlyRate(hourlyrate2);  // Constrói a variável do valor da hora
setHours(hours2);  // Constrói a variável do valor do total de horas trabalhadas
}

// Se o funcionário receber pagamento por horas, retorna este cálculo
public double getPay(){
return hourlyrate*hours;
}

// Devolve para o método getInfo (que prepara a impressão) da classe CEmployee suas informações,
// para finalizar a impressão inicialmente criada em CEmployee.
public String getInfo (){
return super.getInfo() + "\n" + "Pagamento: " +getPay();
}

// Métodos SET para pegar a variável do valor da hora e do total de horas trabalhadas
public void setHourlyRate(double hourlyrate2){
hourlyrate = hourlyrate2;
} 

public void setHours(double hours2){
hours = hours2;
} 
}