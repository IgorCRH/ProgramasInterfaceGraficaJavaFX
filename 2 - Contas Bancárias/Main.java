import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<ContaBancaria> clientes = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaInicial.fxml"));
        Parent root = loader.load();
        
        TelaInicialController telaInicialController = loader.getController();
        telaInicialController.setMain(this);
        telaInicialController.setClientes(clientes);

        primaryStage.setTitle("Sistema Bancário");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public ArrayList<ContaBancaria> getClientes() {
        return clientes;
    }
}
