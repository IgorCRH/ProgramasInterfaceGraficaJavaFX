import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CadastroContaEspecialController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField numContaField;

    @FXML
    private TextField saldoField;

    @FXML
    private TextField limiteField;

    @FXML
    private Button cadastrarButton;

    private ArrayList<ContaBancaria> clientes;
    

    public void setClientes(ArrayList<ContaBancaria> clientes) {
        this.clientes = clientes;
    }
    
    public void showCadastroEspecial() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("teladecadastrocontaesp.fxml"));
    try {
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Conta Especial");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    public void cadastrarButtonClicked() {
        String cliente = nomeField.getText();
        int numConta = Integer.parseInt(numContaField.getText());
        float saldo = Float.parseFloat(saldoField.getText());
        float limite = Float.parseFloat(limiteField.getText());

        ContaEspecial novaContaEspecial = new ContaEspecial(cliente, numConta, saldo, limite);
        clientes.add(novaContaEspecial);

        nomeField.clear();
        numContaField.clear();
        saldoField.clear();
        limiteField.clear();
        
        Stage stage = (Stage) cadastrarButton.getScene().getWindow();
        stage.close();
        
        escreverNoArquivo(clientes);
    }
    
    private void escreverNoArquivo(ArrayList<ContaBancaria> clientes) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
        for (int i = 0; i < clientes.size(); i++) {
            ContaBancaria cliente = clientes.get(i);
            String linha = String.format("%d,%s,%d,%.2f", i + 1, cliente.getCliente(), cliente.getNumConta(), cliente.getSaldo());
            writer.write(linha);
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}