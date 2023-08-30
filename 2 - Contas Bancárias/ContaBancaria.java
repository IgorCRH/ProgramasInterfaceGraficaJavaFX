public class ContaBancaria
{
protected String cliente; // Variáveis da classe
protected int numconta;
protected float saldo;

// Métodos para GET para retornar as variáveis
public String getCliente (){
return cliente;    
}

public int getNumConta (){
return numconta;    
}

public float getSaldo (){
return saldo;    
}

// Construtor da Classe ContaBancaria
public ContaBancaria (String cliente, int numconta, float saldo){
this.cliente = cliente;
this.numconta = numconta;
this.saldo = saldo;    
}

@Override
public String toString() {
  return "Cliente: " + cliente + ", Número da Conta: " + numconta + ", Saldo: " + saldo;
}
}