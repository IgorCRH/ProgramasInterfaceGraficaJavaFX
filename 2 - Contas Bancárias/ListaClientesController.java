import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListaClientesController {

    @FXML
    private TextArea listaClientesTextArea;

    private ArrayList<ContaBancaria> clientes = new ArrayList<>();

    public void setClientes(ArrayList<ContaBancaria> clientes) {
        this.clientes = clientes;
        updateListaClientes();
    }

private void updateListaClientes() {
    StringBuilder clientsText = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0]);
            String nome = fields[1];
            int numConta = Integer.parseInt(fields[2]);
            float saldo = Float.parseFloat(fields[3]);

            // Create the client object based on the parsed data
            ContaBancaria cliente = new ContaBancaria(nome, numConta, saldo);

            // Append the client details to the text area
            clientsText.append(cliente.toString()).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    listaClientesTextArea.setText(clientsText.toString());
}

}

