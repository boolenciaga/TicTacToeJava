package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.sql.SQLException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        User Phillip = new User("JEEP",  "Hamptom", "Phillip", "Chan");

        DatabaseManager.getInstance();
        DatabaseManager.addUser(Phillip);
        Phillip.setUserID(DatabaseManager.getUserId(Phillip));
        System.out.println(Phillip.getUserID());
        Phillip.setUsername("GG");
        Phillip.setLastName("DAM");
        Phillip.setFirstName("Not Phillip");
        System.out.println("GONNA UPDATE USER");
        DatabaseManager.updateUser(Phillip);
        DatabaseManager.deleteUser("BROLO");
        DatabaseManager.deleteUser(3);

    }
}
