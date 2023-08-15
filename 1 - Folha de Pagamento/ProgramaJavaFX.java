import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramaJavaFX extends Application {

    private List<CEmployee> funcionarios = new ArrayList<>();
    private double totalFolha = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Criar os elementos de interface definidos no FXML
        MenuBar menuBar = new MenuBar();

        Label titleLabel = new Label("Menu Principal");
        titleLabel.setStyle("-fx-translate-x: 200px; -fx-font-size: 18px; -fx-font-weight: bold;");

        ImageView imageView = new ImageView(new Image("icone.png"));
        imageView.setFitWidth(110);
        imageView.setFitHeight(110);
        
        HBox imageViewBox = new HBox(imageView);
        imageViewBox.setAlignment(Pos.CENTER_LEFT);
        imageViewBox.setStyle("-fx-translate-x: -360px;"); 

        Button viewGraphButton = new Button("Ver Gráfico de Funcionários");
        viewGraphButton.getStyleClass().add("custom-button");
        viewGraphButton.setOnAction(event -> exibirGrafico());
        
        Button viewFuncionariosButton = new Button("Ver Funcionários Registrados");
        viewFuncionariosButton.getStyleClass().add("custom-button");
        viewFuncionariosButton.setOnAction(event -> exibirFuncionarios());

        Button exitButton = new Button("Sair");
        exitButton.getStyleClass().add("custom-button");
        exitButton.setOnAction(event -> primaryStage.close());

        Button registerButton = new Button("Cadastrar");
        registerButton.getStyleClass().add("custom-button");
        registerButton.setOnAction(event -> cadastrarFuncionario());
       
        VBox buttonsPane = new VBox(10, viewGraphButton, viewFuncionariosButton, registerButton, exitButton);
        buttonsPane.setStyle("-fx-padding: 20px;");

        HBox mainPane = new HBox(buttonsPane, imageViewBox, new Pane()); // Substitua 'new Pane()' pelo conteúdo principal
        mainPane.setPadding(new Insets(10));

        // Cena principal
        Scene scene = new Scene(new BorderPane(mainPane, null, null, menuBar, titleLabel), 500, 250);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        // Configurar a janela
        primaryStage.setTitle("Sistema de Folha de Pagamento");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarFuncionario() {
         Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Cadastrar Funcionário");
            dialog.setHeaderText(null);
            dialog.setContentText("Digite o nome do funcionário:");
            
            Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
            okButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            cancelButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nome -> {
                String[] tipos = {"Assalariado", "Por Horas"};
                ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Assalariado", tipos);
                choiceDialog.setTitle("Tipo de Funcionário");
                choiceDialog.setHeaderText(null);
                choiceDialog.setContentText("Selecione o tipo de funcionário:");
                
                 Button okButtonChoice = (Button) choiceDialog.getDialogPane().lookupButton(ButtonType.OK);
                 Button cancelButtonChoice = (Button) choiceDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
                 okButtonChoice.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                 cancelButtonChoice.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                Optional<String> tipoResult = choiceDialog.showAndWait();
                tipoResult.ifPresent(tipo -> {
                                if ("Assalariado".equals(tipo)) {
                    TextInputDialog salarioDialog = new TextInputDialog();
                    salarioDialog.setTitle("Cadastrar Assalariado");
                    salarioDialog.setHeaderText(null);
                    salarioDialog.setContentText("Digite o salário do funcionário:");
                    
                    Button okButtonType = (Button) salarioDialog.getDialogPane().lookupButton(ButtonType.OK);
                    Button cancelButtonType = (Button) salarioDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
                    okButtonType.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                    cancelButtonType.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                    
                    Optional<String> salarioResult = salarioDialog.showAndWait();
                    salarioResult.ifPresent(salarioStr -> {
                        try {
                            double salario = Double.parseDouble(salarioStr);
                            Salaried funcionario = new Salaried(nome, salario);
                            funcionarios.add(funcionario);
                            mostrarMensagem("Funcionário assalariado registrado com sucesso!");
                        } catch (NumberFormatException e) {
                            mostrarMensagemErro("Digite um valor numérico válido para o salário.");
                        }
                    });
                } else if ("Por Horas".equals(tipo)) {
                    TextInputDialog valorHoraDialog = new TextInputDialog();
                    valorHoraDialog.setTitle("Cadastrar Por Horas");
                    valorHoraDialog.setHeaderText(null);
                    valorHoraDialog.setContentText("Digite o valor da hora do funcionário:");
                    
                    Button okButtonHourValue = (Button) choiceDialog.getDialogPane().lookupButton(ButtonType.OK);
                    Button cancelButtonHourValue = (Button) choiceDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
                    okButtonHourValue.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                    cancelButtonHourValue.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                    
                    Optional<String> valorHoraResult = valorHoraDialog.showAndWait();
                    valorHoraResult.ifPresent(valorHoraStr -> {
                        try {
                            double valorHora = Double.parseDouble(valorHoraStr);

                            TextInputDialog horasTrabalhadasDialog = new TextInputDialog();
                            horasTrabalhadasDialog.setTitle("Cadastrar Por Horas");
                            horasTrabalhadasDialog.setHeaderText(null);
                            horasTrabalhadasDialog.setContentText("Digite o número de horas trabalhadas do funcionário:");
                            
                            Button okButtonHorTrab = (Button) horasTrabalhadasDialog.getDialogPane().lookupButton(ButtonType.OK);
                            Button cancelButtonHorTrab = (Button) horasTrabalhadasDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
                            okButtonHorTrab.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                            cancelButtonHorTrab.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                            
                            Optional<String> horasTrabalhadasResult = horasTrabalhadasDialog.showAndWait();
                            horasTrabalhadasResult.ifPresent(horasTrabalhadasStr -> {
                                try {
                                    double horasTrabalhadas = Double.parseDouble(horasTrabalhadasStr);
                                    Hourly funcionario = new Hourly(nome, valorHora, horasTrabalhadas);
                                    funcionarios.add(funcionario);
                                    mostrarMensagem("Funcionário por horas registrado com sucesso!");
                                } catch (NumberFormatException e) {
                                    mostrarMensagemErro("Digite um valor numérico válido para as horas trabalhadas.");
                                }
                            });
                        } catch (NumberFormatException e) {
                            mostrarMensagemErro("Digite um valor numérico válido para o valor da hora.");
                        }
                    });
                }    
                });
            });
        });
    }

    private void exibirGrafico() {
        if (funcionarios.isEmpty()) {
            mostrarMensagem("Nenhum funcionário registrado.");
        } else {
            int numAssalariados = 0;
            int numPorHora = 0;
            for (CEmployee funcionario : funcionarios) {
                if (funcionario instanceof Salaried) {
                    numAssalariados++;
                } else if (funcionario instanceof Hourly) {
                    numPorHora++;
                }
            }

            PieChart pieChart = new PieChart();
            pieChart.getData().addAll(
                    new PieChart.Data("Assalariados", numAssalariados),
                    new PieChart.Data("Por Hora", numPorHora)
            );

            Stage graphStage = new Stage();
            graphStage.setTitle("Gráfico de Funcionários");
            graphStage.setScene(new Scene(new VBox(pieChart), 400, 400));
            graphStage.show();
        }
    }

    private void exibirFuncionarios() {
        if (funcionarios.isEmpty()) {
            mostrarMensagem("Nenhum funcionário registrado.");
        } else {
            String listaFuncionarios = "";
            for (CEmployee funcionario : funcionarios) {
                listaFuncionarios += funcionario.getInfo() + "\n\n";
            }

            // Crie um TextArea para exibir a lista de funcionários
            TextArea funcionariosTextArea = new TextArea();
            funcionariosTextArea.setText(listaFuncionarios);
            funcionariosTextArea.setEditable(false);

            // Crie um novo stage para exibir a lista de funcionários
            Stage listaFuncionariosStage = new Stage();
            listaFuncionariosStage.setTitle("Lista de Funcionários Registrados");
            listaFuncionariosStage.setScene(new Scene(new VBox(funcionariosTextArea), 400, 300));
            listaFuncionariosStage.show();
        }
    }
    
    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagemErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
