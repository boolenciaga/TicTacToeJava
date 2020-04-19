package app;

import Messages.RegistrationMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modules.User;
import sqlite.DatabaseManager;

import java.io.IOException;

public class addUserController {

    @FXML
    private TextField username;

    @FXML
    private Button createUser;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    @FXML
    private Button backButton;

    @FXML
    void createUserClicked(ActionEvent event) {

        if(!username.getText().isBlank() && !password.getText().isBlank()
            && !firstName.getText().isBlank() && !lastName.getText().isBlank())
        {
            //prep registration message
            User newUser = new User(username.getText(), password.getText(), firstName.getText(), lastName.getText());
            RegistrationMsg regMsg = new RegistrationMsg(newUser);

            try {
                //send registration msg
                Global.toServer.writeObject(regMsg);
                Global.toServer.flush();

                //receive registration status
                boolean successfulInsert = Global.fromServer.readBoolean();

                if(successfulInsert)
                {
                    errorLabel.setTextFill(Color.LIMEGREEN);
                    errorLabel.setText("User successfully added.");
                }
                else
                {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Unable to add user");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Please Enter Valid Information.");
        }
    }


    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        Parent mainWindow = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene mainScene = new Scene(mainWindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

}
