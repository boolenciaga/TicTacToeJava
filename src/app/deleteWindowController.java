package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class deleteWindowController {

    @FXML
    private TextField username;

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    void deleteButtonClicked(ActionEvent event) throws IOException {

        // CHECK IF DELETED -> GO TO MAIN WINDOW
        Parent mainWindow = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene mainScene = new Scene(mainWindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

}
