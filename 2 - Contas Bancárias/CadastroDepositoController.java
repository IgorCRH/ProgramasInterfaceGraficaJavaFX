import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CadastroDepositoController {

    @FXML
    private TextField quantiaDepositoField;

    @FXML
    private TextField numContaDepositoField;

    @FXML
    private Button DepositarButton;

    @FXML
    private TextArea mensagemDepositoTextArea;

    private ContaPoupanca contaPoupanca;
    private ContaEspecial contaEspecial;

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public void setContaEspecial(ContaEspecial contaEspecial) {
        this.contaEspecial = contaEspecial;
    }

@FXML
private void DepositarButtonClicked() {
    int numConta = Integer.parseInt(numContaDepositoField.getText());
    float quantia = Float.parseFloat(quantiaDepositoField.getText());

    try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"));
         BufferedWriter writer = new BufferedWriter(new FileWriter("clientes_temp.txt"))) {

        String line;
        boolean depositoEfetuado = false;

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0]);
            String nome = fields[1];
            int numContaAtual = Integer.parseInt(fields[2]);
            float saldo = Float.parseFloat(fields[3]);

            if (fields.length >= 4 && numContaAtual == numConta) {
                saldo += quantia;
                depositoEfetuado = true;
                mensagemDepositoTextArea.setText("Depósito bem sucedido");
            }

            writer.write(id + "," + nome + "," + numContaAtual + "," + saldo);
            writer.newLine();
        }

        if (!depositoEfetuado) {
            mensagemDepositoTextArea.setText("Erro ao depositar: conta não encontrada");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Rename the temporary file to replace the original file
    try {
        Files.move(Paths.get("clientes_temp.txt"), Paths.get("clientes.txt"), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
