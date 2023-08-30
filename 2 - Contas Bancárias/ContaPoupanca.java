import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ContaPoupanca extends ContaBancaria {
    protected float quantia = 0;

    public ContaPoupanca(String cliente, int numconta, float saldo) {
        super(cliente, numconta, saldo);
    }
    
    public int getNumconta(){
        return numconta;
    }
    
    public void setSaldo(float novosaldo){
    this.saldo = novosaldo;
    }

    public float getSaldo(){
    return saldo;
    }
}