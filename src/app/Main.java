package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modules.User;
import sqlite.DatabaseManager;

import java.sql.SQLException;


public class Main extends Application {


    // ../app/addUserWindow.fxml

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../app/mainWindow.fxml"));
        primaryStage.setTitle("Add User Record Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        launch(args);

    }
}
