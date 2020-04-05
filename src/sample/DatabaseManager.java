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

                System.out.println("ADDING USER SUCCESSFUL\n\n");


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

    public static boolean updateUser(User user)
    {
        if (instance != null)
        {
            synchronized (DatabaseManager.class)
            {
                try {

                    String query = "UPDATE  User " +
                            "SET userName = ? , password = ? , fName = ? , lName = ? " +
                            "WHERE userID = ?";

                    PreparedStatement pst = instance.prepareStatement(query);

                    pst.setString(1,user.getUsername());
                    pst.setString(2,user.getPassword());
                    pst.setString(3,user.getFirstName());
                    pst.setString(4,user.getLastName());
                    pst.setInt(5, user.getUserID());

                    pst.executeUpdate();

                    pst.close();

                    System.out.println("UPDATED USER");

                    return true;

                } catch (SQLException e) {
                    System.out.println("FAILED TO UPDATE USER");
                    e.printStackTrace();

                }
            }
        }

        return false;
    }

    public static void deleteUser(String username)
    {
        try {

            String query = "DELETE FROM USER " +
                           "WHERE userName = ?";
            PreparedStatement pst = instance.prepareStatement(query);

            pst.setString(1, username);

            pst.executeUpdate();

            System.out.println("DELETED " + username + "!!!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id)
    {
        try {

            String query = "DELETE FROM USER " +
                           "WHERE userID = ?";
            PreparedStatement pst = instance.prepareStatement(query);

            pst.setInt(1, id);

            pst.executeUpdate();

            System.out.println("DELETED ID# " + id + "!!!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
