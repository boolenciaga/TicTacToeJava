package app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import modules.BaseModel;
import modules.User;
import sqlite.DatabaseManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class serverUsersController implements Initializable {

    @FXML
    ListView usersList;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        List<BaseModel> list = DatabaseManager.getInstance().list(new User());
        usersList.getItems().addAll(list);

    }


}
