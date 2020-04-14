package sqlite;

import modules.BaseModel;
import modules.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DatabaseManager implements DataSource {
    private static DatabaseManager instance = null;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private DatabaseManager() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:Database\\TicTacToeDB.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseManager getInstance()
    {
        if (instance == null)
        {
            synchronized (DatabaseManager.class)
            {
                if (instance == null)
                {
                    try {
                        instance = new DatabaseManager();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    // Class.forName("org.sqlite.JDBC");
                        //instance = DriverManager.getConnection("jdbc:sqlite:Database\\TicTacToeDB.db");
                        System.out.println("Database Connection SUCCESSFUL\n");

                    return instance;

                }
            }
        }

        return instance;

    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        if(instance != null)
        {
            synchronized (DatabaseManager.class)
            {

                String query = "INSERT INTO User(fName,lName,password,dateCreated,userName,status)" +
                               "VALUES (?,?,?,?,?,?)";

                PreparedStatement pst =  getConnection().prepareStatement(query);

                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getCreation());
                pst.setString(5, user.getUsername());
                pst.setString(6, user.getStatus());

                if(pst.execute())
                {

                }

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

    public  boolean updateUser(User user)
    {
        if (instance != null)
        {
            synchronized (DatabaseManager.class)
            {
                try {

                    String query = "UPDATE  User " +
                            "SET userName = ? , password = ? , fName = ? , lName = ? " +
                            "WHERE userID = ?";

                    PreparedStatement pst =  getConnection().prepareStatement(query);

                    pst.setString(1,user.getUsername());
                    pst.setString(2,user.getPassword());
                    pst.setString(3,user.getFirstName());
                    pst.setString(4,user.getLastName());
                    pst.setInt(5, user.getId());

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

    public  void deleteUser(String username)
    {
        try {

            String query = "DELETE FROM USER " +
                           "WHERE userName = ?";
            PreparedStatement pst = getConnection().prepareStatement(query);

            pst.setString(1, username);

            pst.executeUpdate();

            System.out.println("DELETED " + username + "!!!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void deleteUser(int id)
    {
        try {

            String query = "DELETE FROM USER " +
                           "WHERE userID = ?";


            PreparedStatement pst =  getConnection().prepareStatement(query);

            pst.setInt(1, id);

            pst.executeUpdate();

            System.out.println("DELETED ID# " + id + "!!!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public  List<String> getAllUser()
    {
        List <String> list = new ArrayList<String>();

        try {
            String query = "SELECT userName "
                        +  "FROM User "
                        +  "WHERE userID > ?";

            PreparedStatement pstmt  =  getConnection().prepareStatement(query);

            pstmt.setDouble(1, 1);

            ResultSet rs  = pstmt.executeQuery();

            while(rs.next())
            {
                list.add(rs.getString("userName"));
            }

            System.out.println("ABLE TO GET ALL REGISTERED USERS\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public  List<String> getUsers(String status)
    {
        List <String> users = new ArrayList<String>();
        String stat;

        if("OFFLINE".equalsIgnoreCase(status))
        {
            stat = "OFFLINE";
        }
        else
        {
            stat = "ONLINE";
        }

        try {
            String query = "SELECT userName "
                    +  "FROM User "
                    +  "WHERE status = ?";

            PreparedStatement pstmt  =  getConnection().prepareStatement(query);

            pstmt.setString(1, stat);

                    ResultSet rs  = pstmt.executeQuery();

            while(rs.next())
            {
                users.add(rs.getString("userName"));
            }

            System.out.println("ABLE TO GET ALL " + stat +  " USERS\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public  int getUserId(User user) {
        int id = 0;

        try {
            String query = "SELECT userID " +
                           "FROM User " +
                           "WHERE userName = ?";

            PreparedStatement pstmt  =   getConnection().prepareStatement(query);

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

    /*
          String query = "INSERT INTO User(fName,lName,password,dateCreated,userName,status)" +
                               "VALUES (?,?,?,?,?,?)";

                PreparedStatement pst =  getConnection().prepareStatement(query);

                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getCreation());
                pst.setString(5, user.getUsername());
                pst.setString(6, user.getStatus());

                if(pst.execute())
                {

                }

                System.out.println("ADDING USER SUCCESSFUL\n\n");


                pst.close();

     */


    @Override
    public BaseModel insert(BaseModel obj) {
        StringBuilder qryBuilder = new StringBuilder();
        qryBuilder.append("INSERT INTO ");

        if(obj instanceof User)
        {
            qryBuilder.append("User (fName,lName,password,dateCreated,userName,status) " +
                              "VALUES (?,?,?,?,?,?)");
        }

        return null;
    }

    @Override
    public BaseModel delete(BaseModel obj) {
        return null;
    }

    @Override
    public BaseModel update(BaseModel obj) {
        return null;
    }

    @Override
    public BaseModel get(int id) {
        return null;
    }

    @Override
    public List<BaseModel> list(BaseModel Obj) {
        return null;
    }

    @Override
    public List<BaseModel> query(BaseModel obj, String filter) {
        return null;
    }

    private void executeInsert(String query)
    {

    }

    private void executeDelete(String query)
    {

    }

    private void executeUpdate(String query)
    {

    }

    private ResultSet executeQuery(String query) throws SQLException {
            PreparedStatement pstmt = null;
            ResultSet rs = pstmt.executeQuery();

            return rs;
    }
}
