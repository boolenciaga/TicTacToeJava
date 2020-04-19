package sqlite;

import modules.*;

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
            this.connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Phillip\\Documents\\GitHub\\TicTacToeJava\\Database\\TicTacToeDB.db");
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
//
//    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
//        if(instance != null)
//        {
//            synchronized (DatabaseManager.class)
//            {
//
//                String query = "INSERT INTO User(fName,lName,password,dateCreated,userName,status)" +
//                               "VALUES (?,?,?,?,?,?)";
//
//                PreparedStatement pst =  getConnection().prepareStatement(query);
//
//                pst.setString(1, user.getFirstName());
//                pst.setString(2, user.getLastName());
//                pst.setString(3, user.getPassword());
//                pst.setString(4, user.getCreation());
//                pst.setString(5, user.getUsername());
//                pst.setString(6, user.getStatus());
//
//                if(pst.execute())
//                {
//
//                }
//
//                System.out.println("ADDING USER SUCCESSFUL\n\n");
//
//
//                pst.close();
//            }
//        }
//        else
//        {
//            System.out.println("Add query failed.");
//            return false;
//        }
//
//        return true;
//    }
//
//    public  boolean updateUser(User user)
//    {
//        if (instance != null)
//        {
//            synchronized (DatabaseManager.class)
//            {
//                try {
//
//                    String query = "UPDATE  User " +
//                            "SET userName = ? , password = ? , fName = ? , lName = ? " +
//                            "WHERE userID = ?";
//
//                    PreparedStatement pst =  connection.prepareStatement(query);
//
//                    pst.setString(1,user.getUsername());
//                    pst.setString(2,user.getPassword());
//                    pst.setString(3,user.getFirstName());
//                    pst.setString(4,user.getLastName());
//                    pst.setInt(5, user.getId());
//
//                    pst.executeUpdate();
//
//                    pst.close();
//
//                    System.out.println("UPDATED USER");
//
//                    return true;
//
//                } catch (SQLException e) {
//                    System.out.println("FAILED TO UPDATE USER");
//                    e.printStackTrace();
//
//                }
//            }
//        }
//
//        return false;
//    }
//
//    public void deleteUser(String username)
//    {
//        try {
//
//            String query = "DELETE FROM USER " +
//                           "WHERE userName = ?";
//            PreparedStatement pst = getConnection().prepareStatement(query);
//
//            pst.setString(1, username);
//
//            pst.executeUpdate();
//
//            System.out.println("DELETED " + username + "!!!\n");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public  void deleteUser(int id)
//    {
//        try {
//
//            String query = "DELETE FROM USER " +
//                           "WHERE userID = ?";
//
//
//            PreparedStatement pst =  getConnection().prepareStatement(query);
//
//            pst.setInt(1, id);
//
//            pst.executeUpdate();
//
//            System.out.println("DELETED ID# " + id + "!!!\n");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//
//    public  List<String> getAllUser()
//    {
//        List <String> list = new ArrayList<String>();
//
//        try {
//            String query = "SELECT userName "
//                        +  "FROM User "
//                        +  "WHERE userID > ?";
//
//            PreparedStatement pstmt  =  getConnection().prepareStatement(query);
//
//            pstmt.setDouble(1, 1);
//
//            ResultSet rs  = pstmt.executeQuery();
//
//            while(rs.next())
//            {
//                list.add(rs.getString("userName"));
//            }
//
//            System.out.println("ABLE TO GET ALL REGISTERED USERS\n");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return list;
//    }
//
//    public  List<String> getUsers(String status)
//    {
//        List <String> users = new ArrayList<String>();
//        String stat;
//
//        if("OFFLINE".equalsIgnoreCase(status))
//        {
//            stat = "OFFLINE";
//        }
//        else
//        {
//            stat = "ONLINE";
//        }
//
//        try {
//            String query = "SELECT userName "
//                    +  "FROM User "
//                    +  "WHERE status = ?";
//
//            PreparedStatement pstmt  =  getConnection().prepareStatement(query);
//
//            pstmt.setString(1, stat);
//
//                    ResultSet rs  = pstmt.executeQuery();
//
//            while(rs.next())
//            {
//                users.add(rs.getString("userName"));
//            }
//
//            System.out.println("ABLE TO GET ALL " + stat +  " USERS\n");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return users;
//    }
//
//    public  int getUserId(User user) {
//        int id = 0;
//
//        try {
//            String query = "SELECT userID " +
//                           "FROM User " +
//                           "WHERE userName = ?";
//
//            PreparedStatement pstmt  =   getConnection().prepareStatement(query);
//
//            pstmt.setString(1, user.getUsername());
//
//            ResultSet  rs   = pstmt.executeQuery();
//
//            id  = rs.getInt("userID");
//
//            System.out.println("Got User ID!");
//
//        } catch (SQLException e) {
//
//            System.out.println("FAILED TO GET USER ID");
//            e.printStackTrace();
//        }
//
//        return id;
//    }

    @Override
    public BaseModel insert(BaseModel obj) {
        StringBuilder qryBuilder = new StringBuilder();
        qryBuilder.append("INSERT INTO ");

        if(obj instanceof User)
        {
            User u = (User) obj;

            qryBuilder.append("User (fName,lName,password,dateCreated,userName,status) " +
                              "VALUES (\'" +  u.getFirstName() + "\', \'" + u.getLastName() + "\', \'" + u.getPassword() + "\', \'" +
                              u.getCreation() + "\', \'" + u.getUsername() + "\', \'" + u.getStatus() + "\')" );
        }
        else if(obj instanceof Game)
        {
            Game g = (Game) obj;

            qryBuilder.append("Game (id,p1Id,starterId) " +
                              "VALUES (" + g.getId() + ", " + g.getP1Id() + ", " + g.getStarterId() + ')');
        }
        else if(obj instanceof Moves)
        {
            Moves m = (Moves) obj;

            qryBuilder.append("Moves (id,gameId,playerId,X_coord,Y_coord,time) " +
                              "VALUES (" + m.getId() + ", " + m.getGameId() +  ", " + m.getPlayerId() + ", " + m.getXcoord()
                                         + ", " + m.getYcoord() + ", \'" + m.getTime() + "\')" );
        }
        else if(obj instanceof GameViewers)
        {
            GameViewers gv = (GameViewers) obj;

            qryBuilder.append("GameViewers (gameId,viewerId) " +
                              "VALUES (" + gv.getGameId() + ", " + gv.getId() + ')');
        }

        try {
            executeInsert(qryBuilder.toString());
            System.out.println("Insertion worked\n\n");
            return null;
        } catch (SQLException e) {
            System.out.println("error executing insert\n\n");
            e.printStackTrace();
            return obj;
        }

    }

    @Override
    public BaseModel delete(BaseModel obj) {
        StringBuilder qryBuilder = new StringBuilder();
        qryBuilder.append("UPDATE ");

        if(obj instanceof User)
        {
            User u = (User) obj;

            qryBuilder.append("User " +
                              "SET status = 'INACTIVE' " +
                              "WHERE userID = \'" + u.getUserID() + "\'");
        }

        try {
            executeDelete(qryBuilder.toString());
            System.out.println("DELETED SUCCESS\n\n");
            return null;
        } catch (SQLException e) {
            System.out.println("DELETED FAIL\n\n");
            e.printStackTrace();
            return obj;
        }

    }


    @Override
    public BaseModel update(BaseModel obj) {
        StringBuilder qryBuilder = new StringBuilder();
        qryBuilder.append("UPDATE ");

        if(obj instanceof User)
        {
            User u = (User) obj;

            qryBuilder.append("User " +
                              "SET userName = \'" + u.getUsername()  + "\', password = \'" + u.getPassword() + "\', fName = \'"
                                                  + u.getFirstName() + "\', lName = \'"    + u.getLastName() + "\', status = \'"
                                                  + u.getStatus()    + "\' "               +
                              "WHERE userID = \'" + u.getUserID() + "\'");
        }

        try {
            executeUpdate(qryBuilder.toString());
            System.out.println("UPDATE QUERY Successful\n\n");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("UNABLE to update QUERY");
            return obj;
        }
    }

    @Override
    public BaseModel get(int id) {
        return null;
    }

    @Override
    public BaseModel authenticate(String username, String password) {

        String query = "SELECT * "
                +  "FROM User "
                +  "WHERE userName = \'" + username + "\' "
                +  "AND   password = \'" + password + "\'";

        try {
            ResultSet rs = executeQuery(query);
            User u = new User(rs.getString("userName"), rs.getString("password"), rs.getString("fName"), rs.getString("lName"));
            u.setUserID(Integer.parseInt(rs.getString("userID")));
            System.out.println("User Authenticated\n\n");
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("User Not Authenticated\n\n");
            return null;
        }

    }

    @Override
    public List <BaseModel> list(BaseModel obj) {
        List <BaseModel> list = new ArrayList<>();

        if(obj instanceof User) {

            String query = "SELECT userName "
                        +  "FROM User "
                        +  "WHERE userID > 1 "
                        +  "AND  status != 'INACTIVE' ";

            try {
                ResultSet rs = executeQuery(query);

                while(rs.next())
                {
                    User u = new User(rs.getString("userName"));
                    list.add(u);
                }

                System.out.println("Got all QUERY \n\n");

            } catch (SQLException e) {
                System.out.println("Error Get All Query");
                e.printStackTrace();
            }
        }
        else if (obj instanceof Game)
        {

        }

        return list;
    }


    @Override
    public List<BaseModel> query(BaseModel obj, String filter) {

        List <BaseModel> list = new ArrayList<>();
        StringBuilder qryBuilder = new StringBuilder();
        qryBuilder.append("SELECT ");

        if(obj instanceof User)
        {
            qryBuilder.append("* " +
                              "FROM User " +
                               filter );

            try {
                ResultSet rs = executeQuery(qryBuilder.toString());

                while(rs.next())
                {
                    User u = new User(rs.getString("userName"));
                    list.add(u);
                }

                System.out.println("Got all QUERY \n\n");

            } catch (SQLException e) {
                System.out.println("Error Get All Query");
                e.printStackTrace();
                return null;
            }

        }
        else if (obj instanceof Game)
        {
            Game g = (Game) obj;

            qryBuilder.append("* " +
                              "FROM Game " +
                              "WHERE starterID = \'" + filter + "\'");

            try {
                ResultSet rs = executeQuery(qryBuilder.toString());

                while(rs.next())
                {
                    Game game = new Game(Integer.parseInt(rs.getString("id")), rs.getString("startTime"),
                                         rs.getString("endTime"), Integer.parseInt(rs.getString("p1Id")),
                                        Integer.parseInt(rs.getString("p2Id")),
                                        Integer.parseInt(rs.getString("starterId")),Integer.parseInt(rs.getString("winnerId")));
                    list.add(game);
                }

                System.out.println("Got all QUERY \n\n");

            } catch (SQLException e) {
                System.out.println("Error Get All Query");
                e.printStackTrace();
                return null;
            }

        }


        return list;
    }



    private void executeInsert(String query) throws SQLException {

        PreparedStatement pst = connection.prepareStatement(query);
        pst.execute();
        pst.close();
    }

    private void executeDelete(String query) throws SQLException {

        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();

    }

    private void executeUpdate(String query) throws SQLException {
        PreparedStatement pst =  connection.prepareStatement(query);
        pst.executeUpdate();

    }

    private ResultSet executeQuery(String query) throws SQLException {

        PreparedStatement pstmt  =  connection.prepareStatement(query);
        ResultSet rs  = pstmt.executeQuery();
        return rs;

    }
}
