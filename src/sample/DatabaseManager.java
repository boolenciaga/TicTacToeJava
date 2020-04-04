package sample;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DatabaseManager {
    private static Connection instance = null;

    private DatabaseManager() {

    }

    public static Connection getInstance()
    {
        if (instance == null)
        {
            synchronized (DatabaseManager.class)
            {
                if (instance == null)
                {
                    try {
                        Class.forName("org.sqlite.JDBC");
                        instance = DriverManager.getConnection("jdbc:sqlite:Database\\TicTacToeDB.db");
                        System.out.println("Database Connection SUCCESSFUL");

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }

                    return instance;

                }
            }
        }

        return instance;

    }

    public static boolean addUser(User user) throws SQLException {
        if(instance != null)
        {
            String query = "INSERT INTO User (fName,lName,password,dateCreated)" +
                           "VALUES (?,?,?,?)";

            PreparedStatement pst = instance.prepareStatement(query);

            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getPassword());
            pst.setString(4, "2020-04-04");

            pst.execute();

            System.out.println("Qry successful");

            pst.close();

        }
        else
        {
            System.out.println("Add query failed.");
            return false;
        }
        return true;
    }

    public boolean updateUser(User user)
    {
        return true;
    }

    public void deleteUser(String username)
    {

    }

    public void deleteUser(int userId)
    {

    }


    public List<User> getAllUser()
    {
        List <User> list = new ArrayList<User>();
        return list;
    }

    public List<User> getUsers(String filter)
    {
        List <User> users = new ArrayList<User>();

        return users;
    }



}
