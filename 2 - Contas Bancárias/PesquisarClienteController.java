import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PesquisarClienteController {

    @FXML
    private TextField pesquisarField;

    @FXML
    private Button pesquisarButton;

    @FXML
    private void pesquisarButtonClicked() {
        String nomePesquisar = pesquisarField.getText();
        StringBuilder clientesEncontrados = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4 && fields[1].equalsIgnoreCase(nomePesquisar)) {
                    String nome = fields[1];
                    int numConta = Integer.parseInt(fields[2]);
                    double saldo = Double.parseDouble(fields[3]);
                    clientesEncontrados.append("Nome: ").append(nome).append("\n");
                    clientesEncontrados.append("Número da Conta: ").append(numConta).append("\n");
                    clientesEncontrados.append("Saldo: ").append(saldo).append("\n\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clientesEncontrados.length() > 0) {
            // Crie uma janela para exibir os resultados
            AnchorPane resultadoPane = new AnchorPane();
            resultadoPane.setStyle("-fx-background-color: #229ed0;");

            Label resultadoLabel = new Label("Clientes Encontrados:");
            resultadoLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            resultadoLabel.setLayoutX(20);
            resultadoLabel.setLayoutY(20);

            TextArea detalhesTextArea = new TextArea(clientesEncontrados.toString());
            detalhesTextArea.setLayoutX(20);
            detalhesTextArea.setLayoutY(50);
            detalhesTextArea.setEditable(false); // Impede que o usuário edite o texto
            detalhesTextArea.setPrefWidth(380);

            resultadoPane.getChildren().addAll(resultadoLabel, detalhesTextArea);

            Stage stage = new Stage();
            stage.setTitle("Resultado da Pesquisa");
            stage.setScene(new Scene(resultadoPane, 700, 600));
            stage.show();
        } else {
        AnchorPane resultadoPane = new AnchorPane();
            resultadoPane.setStyle("-fx-background-color: #229ed0;");

            Label resultadoLabel = new Label("Clientes Encontrados:");
            resultadoLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            resultadoLabel.setLayoutX(20);
            resultadoLabel.setLayoutY(20);

            TextArea detalhesTextArea = new TextArea("Nenhum cliente encontrado");
            detalhesTextArea.setLayoutX(20);
            detalhesTextArea.setLayoutY(50);
            detalhesTextArea.setEditable(false); // Impede que o usuário edite o texto

            resultadoPane.getChildren().addAll(resultadoLabel, detalhesTextArea);

            Stage stage = new Stage();
            stage.setTitle("Resultado da Pesquisa");
            stage.setScene(new Scene(resultadoPane, 200, 100));
            stage.show();
        }
    }
}
