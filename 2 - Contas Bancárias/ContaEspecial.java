// Importa as classes
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.awt.event.ActionListener; // Classes de criação de ações
import java.awt.event.ActionEvent; // Classe de criação dos eventos das ações

public class ContaEspecial extends ContaBancaria
{
protected float limite; // Variável exclusiva da classe
protected float quantia = 0;

// Construtor da Classe
public ContaEspecial (String cliente, int numconta, float saldo, float limite){
super(cliente,numconta,saldo);
this.limite = limite;
}

public int getNumconta(){
    return numconta;
}

public void setSaldo(float novosaldo){
this.saldo = novosaldo;
}

public float GetLimite (){
return limite;
}

    public float getSaldo(){
    return saldo;
    }
}