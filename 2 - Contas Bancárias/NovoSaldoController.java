import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NovoSaldoController {

    @FXML
    private TextArea resultadonovoSaldoField;

    @FXML
    private TextField rendimentoField;

    @FXML
    private TextField numcontaRendimentoField;

    @FXML
    private Button RendimentoButton;

    private ContaPoupanca contaPoupanca;

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }
    
    public void setNumeroConta(int numConta) {
        numcontaRendimentoField.setText(String.valueOf(numConta));
    }

    @FXML
    private void initialize() {
        RendimentoButton.setOnAction(event -> {
            try {
                int numConta = Integer.parseInt(numcontaRendimentoField.getText());
                float rendimento = Float.parseFloat(rendimentoField.getText());

                float novoSaldo = calcularNovoSaldo(numConta, rendimento);
                resultadonovoSaldoField.setText("Futuro saldo a partir do rendimento: " + novoSaldo);
            } catch (NumberFormatException ex) {
                resultadonovoSaldoField.setText("Valores inválidos para o número da conta ou rendimento");
            }
        });
    }

    private float calcularNovoSaldo(int numConta, float rendimento) {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String nome = fields[1];
                int numContaAtual = Integer.parseInt(fields[2]);
                float saldo = Float.parseFloat(fields[3]);

                if (numContaAtual == numConta) {
                        return saldo * rendimento;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0; // Cliente não encontrado ou não é conta poupança
    }
}
