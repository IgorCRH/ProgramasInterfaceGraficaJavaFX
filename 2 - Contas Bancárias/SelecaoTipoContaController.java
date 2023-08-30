import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class SelecaoTipoContaController {

    private Main main;
    private TelaInicialController telaInicialController;
    private CadastroPoupancaController cadastroPoupancaController;
    
    public void setMain(Main main) {
        this.main = main;
    }

    public void setControllers(TelaInicialController telaInicialController) {
        this.telaInicialController = telaInicialController;
    }

    @FXML
    private ChoiceBox<String> tipoContaChoiceBox;
    
    @FXML
    private void initialize(){
    tipoContaChoiceBox.getItems().addAll("Conta Poupança", "Conta Especial");
    }
    
    public void setCadastroPoupancaController(CadastroPoupancaController cadastroPoupancaController) {
        this.cadastroPoupancaController = cadastroPoupancaController;
    }
    
    @FXML
    private void selecionarTipoConta() {
    try {
        String tipoContaSelecionado = tipoContaChoiceBox.getValue();
        if (tipoContaSelecionado != null) {
            if (tipoContaSelecionado.equals("Conta Poupança")) {
                telaInicialController.cadcontapoup.showCadastroPoupanca();
            } else if (tipoContaSelecionado.equals("Conta Especial")) {
                telaInicialController.cadcontaesp.showCadastroEspecial();
            }
        }
    } catch (NullPointerException e) {
        e.printStackTrace(); 
    }
}
}