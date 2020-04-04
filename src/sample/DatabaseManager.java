package sample;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static DatabaseManager instance = null;

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance()
    {
        if (instance == null)
        {
            synchronized (DatabaseManager.class)
            {
                if (instance == null)
                {
                    instance = new DatabaseManager();
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
