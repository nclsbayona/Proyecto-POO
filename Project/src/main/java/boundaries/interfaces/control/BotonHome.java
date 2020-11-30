package boundaries.interfaces.control;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BotonHome {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Cliente;

    @FXML
    private Button btn_Obra;

    @FXML
    private Button btn_Compra;

    @FXML
    void goToClienteMain(ActionEvent event) {

        String nomFXML = "Clientes.fxml";
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
        } catch (IOException e) {

        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Menu Cliente");
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    void goToCompraMain(ActionEvent event) {

        String nomFXML = "Compra.fxml";
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
        } catch (IOException e) {

        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Menu Cliente");
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    void goToObraMain(ActionEvent event) {

        String nomFXML = "Obra.fxml";
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
        } catch (IOException e) {

        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Menu Cliente");
        stage.setScene(scene);
        stage.showAndWait();

    }













    @FXML
    void initialize() {
        assert btn_Cliente != null : "fx:id=\"btn_Cliente\" was not injected: check your FXML file 'Home-Galeria.fxml'.";
        assert btn_Obra != null : "fx:id=\"btn_Obra\" was not injected: check your FXML file 'Home-Galeria.fxml'.";
        assert btn_Compra != null : "fx:id=\"btn_Compra\" was not injected: check your FXML file 'Home-Galeria.fxml'.";

    }
}


