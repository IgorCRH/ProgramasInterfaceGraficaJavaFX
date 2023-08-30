import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaSaqueController {
    @FXML
    private TextField quantiaField;

    @FXML
    private TextField numContaField;

    private ContaPoupanca contaPoupanca;
    private ContaEspecial contaEspecial;
    private int numeroConta;

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public void setContaEspecial(ContaEspecial contaEspecial){
    this.contaEspecial = contaEspecial;
    }
    
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
        numContaField.setText(String.valueOf(numeroConta));
    }

    @FXML
    private void sacar() {
        String numContaStr = numContaField.getText();
        if (!numContaStr.isEmpty()) {
            int numConta = Integer.parseInt(numContaStr);
            if (numConta == numeroConta) {
                try {
                    float quantia = Float.parseFloat(quantiaField.getText());
                    if (quantia <= contaPoupanca.saldo) {
                        contaPoupanca.saldo -= quantia;
                        showAlert("Saque realizado com sucesso!", "Saque no valor de " + quantia + " realizado com sucesso!");
                    } else if (quantia <= contaEspecial.saldo && quantia <= contaEspecial.limite){
                        contaEspecial.saldo -= quantia; 
                    } else {
                        showAlert("Saldo insuficiente", "Saldo insuficiente para realizar o saque.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Valor inválido", "A quantia informada é inválida.");
                }
            } else {
                showAlert("Número de conta inválido", "O número de conta informado não corresponde à conta atual.");
            }
        } else {
            showAlert("Número de conta inválido", "Por favor, informe o número da conta.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
