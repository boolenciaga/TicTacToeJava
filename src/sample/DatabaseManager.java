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

    public boolean addUser(User user)
    {
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
