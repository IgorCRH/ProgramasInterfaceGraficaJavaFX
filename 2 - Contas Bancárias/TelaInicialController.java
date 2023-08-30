import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;


public class TelaInicialController {

    private SelecaoTipoContaController selecaocontroller;
    private CadastroPoupancaController cadpoupcontroller = new CadastroPoupancaController ();

    private ArrayList<ContaBancaria> clientes = cadpoupcontroller.getClientes();
    
    public void setControllers(SelecaoTipoContaController selecaocontroller){
        this.selecaocontroller = selecaocontroller;
    }
    
    @FXML
    private Button depositarContaButton;
    
    @FXML
    private Button listaClienteButton;
    
    @FXML
    private Button cadastrarClienteButton;
    
    @FXML
    private Button sacarContaButton;
    
    @FXML
    private Button exibenovoSaldoButton;
    
    @FXML
    private Button pesquisarButton;
    
    @FXML
    private Button SairButton;
    
    private Main main;
    CadastroPoupancaController cadcontapoup = new CadastroPoupancaController();
    CadastroContaEspecialController cadcontaesp = new CadastroContaEspecialController();
   
    public void setMain(Main main) {
        this.main = main;
        this.clientes = main.getClientes();
    }
    
    public void setClientes(ArrayList<ContaBancaria> clientes) {
        this.clientes = clientes;
    }
    
    @FXML
    public void initialize() {
    cadastrarClienteButton.setOnAction(event -> cadastrarClienteButtonClicked());    
    }
    
    @FXML
private void cadastrarClienteButtonClicked() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("selecionartipoconta.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Selecionar Tipo de Conta");
        stage.setScene(new Scene(root));
        stage.show();

        SelecaoTipoContaController selecaoTipoContaController = loader.getController();
        selecaoTipoContaController.setMain(main);
        selecaoTipoContaController.setControllers(this);
        selecaoTipoContaController.setCadastroPoupancaController(cadpoupcontroller);
        
        // Agora, carregue a tela de lista de clientes corretamente
        FXMLLoader listaClientesLoader = new FXMLLoader(getClass().getResource("listaclientes.fxml"));
        Parent listaClientesRoot = listaClientesLoader.load();
        ListaClientesController listaClientesController = listaClientesLoader.getController();
        listaClientesController.setClientes(clientes);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    @FXML
    private void listaClienteButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listaclientes.fxml"));
            Parent root = loader.load();

            // Set the controller for ListaClientes.fxml
            ListaClientesController listaClientesController = loader.getController();
            listaClientesController.setClientes(clientes); // Pass the list of clients

            Stage stage = new Stage();
            stage.setTitle("Lista de Clientes");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void pesquisarClienteButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pesquisarcliente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Pesquisar Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void exibirNovoSaldoButtonClicked(){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("novosaldorendimento.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Exibindo o novo Saldo a partir do Rendimento");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void depositarButtonClicked(){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teladeposito.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Depósito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void sacarButtonClicked(){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telasaque.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Saque");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
        @FXML
    private void sairButtonClicked() {
        Platform.exit();
    }
}