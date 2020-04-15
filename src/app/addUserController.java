package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modules.User;
import sqlite.DatabaseManager;

import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;

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
    void createrUserClicked(ActionEvent event) {

        if(!username.getText().isBlank() && !password.getText().isBlank() && !firstName.getText().isBlank() &&
           !lastName.getText().isBlank())
        {
            User newUser = new User(username.getText(), password.getText(), firstName.getText(), lastName.getText());
            Object valid = DatabaseManager.getInstance().insert(newUser);
            if(valid == null)
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
        else
        {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Please Enter Valid Information.");
        }
    }

}
