package boundaries.interfaces.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControlBotones {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butA;

    @FXML
    private Button butB;

    @FXML
    private Button butC;

    @FXML
    private Button butD;

    @FXML
    private Button butE;

    @FXML
    private Button butF;

    @FXML
    private Button butG;

    @FXML
    private Button butH;

    @FXML
    private Button butI;

    @FXML
    private Button butJ;

    @FXML
    private Button butK;

    @FXML
    void changeToA(ActionEvent event) {
        String nomFXML = "UIVacia.fxml";
        Stage primaryStage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
        } catch (IOException e) {
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle(nomFXML);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
    }

    @FXML
    void changeToB(ActionEvent event) {

    }

    @FXML
    void changeToC(ActionEvent event) {

    }

    @FXML
    void changeToD(ActionEvent event) {

    }

    @FXML
    void changeToE(ActionEvent event) {

    }

    @FXML
    void changeToF(ActionEvent event) {

    }

    @FXML
    void changeToG(ActionEvent event) {

    }

    @FXML
    void changeToH(ActionEvent event) {

    }

    @FXML
    void changeToI(ActionEvent event) {

    }

    @FXML
    void changeToJ(ActionEvent event) {

    }

    @FXML
    void changeToK(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert butA != null : "fx:id=\"butA\" was not injected: check your FXML file 'All.fxml'.";
        assert butB != null : "fx:id=\"butB\" was not injected: check your FXML file 'All.fxml'.";
        assert butC != null : "fx:id=\"butC\" was not injected: check your FXML file 'All.fxml'.";
        assert butD != null : "fx:id=\"butD\" was not injected: check your FXML file 'All.fxml'.";
        assert butE != null : "fx:id=\"butE\" was not injected: check your FXML file 'All.fxml'.";
        assert butF != null : "fx:id=\"butF\" was not injected: check your FXML file 'All.fxml'.";
        assert butG != null : "fx:id=\"butG\" was not injected: check your FXML file 'All.fxml'.";
        assert butH != null : "fx:id=\"butH\" was not injected: check your FXML file 'All.fxml'.";
        assert butI != null : "fx:id=\"butI\" was not injected: check your FXML file 'All.fxml'.";
        assert butJ != null : "fx:id=\"butJ\" was not injected: check your FXML file 'All.fxml'.";
        assert butK != null : "fx:id=\"butK\" was not injected: check your FXML file 'All.fxml'.";

    }
}
