package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class menuWindowController {

    @FXML
    private Button updateAccountButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button logoutButton;

    @FXML
    void deleteAccountClicked(ActionEvent event) throws IOException {
        Parent deleteWindow = FXMLLoader.load(getClass().getResource("deleteWindow.fxml"));
        Scene deleteScene = new Scene(deleteWindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(deleteScene);
        window.show();
    }

    @FXML
    void updateAccountClicked(ActionEvent event) throws IOException {
        Parent updateWindow = FXMLLoader.load(getClass().getResource("updateWindow.fxml"));
        Scene updateScene = new Scene(updateWindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(updateScene);
        window.show();
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) throws IOException {
        Parent mainWindow = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene mainScene = new Scene(mainWindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

}
