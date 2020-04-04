package sample;

import javax.xml.transform.Result;
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
            synchronized (DatabaseManager.class)
            {
                String query = "INSERT INTO User(fName,lName,password,dateCreated,userName)" +
                               "VALUES (?,?,?,?,?)";

                PreparedStatement pst = instance.prepareStatement(query);

                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getPassword());
                pst.setString(4, "2020-04-04");
                pst.setString(5, user.getUsername());

                pst.execute();

                System.out.println("Qry successful");


                pst.close();
            }
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
        if (instance != null)
        {
            synchronized (DatabaseManager.class)
            {
                String query = "Update User (fName,lName,password,dateCreated)" +
                        "VALUES (?,?,?,?)";
            }
        }
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

    public static int getUserId(User user) {
        int id = 0;

        try {
            String query = "SELECT userID " +
                           "FROM User " +
                           "WHERE userName = ?";

            PreparedStatement pstmt  =  instance.prepareStatement(query);

            pstmt.setString(1, user.getUsername());

            ResultSet  rs   = pstmt.executeQuery();

            id  = rs.getInt("userID");

            System.out.println("Got User ID!");

        } catch (SQLException e) {

            System.out.println("FAILED TO GET USER ID");
            e.printStackTrace();
        }

        return id;
    }


}
