package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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
    void createrUserClicked(ActionEvent event) {

        if(!username.getText().isBlank() && !password.getText().isBlank() && !firstName.getText().isBlank() &&
           !lastName.getText().isBlank())
        {
            User newUser = new User(username.getText(), password.getText(), firstName.getText(), lastName.getText());
            DatabaseManager.getInstance();
            try {
                DatabaseManager.addUser(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
        {

        }
    }

}
