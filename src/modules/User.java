package modules;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class User extends BaseModel{

    private int    userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String created;
    private String status;

    public User(String username, String password, String firstName, String lastName)
    {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setStatus("OFFLINE");
        setCreated();
    }

    public User(String username)
    {
        setUsername(username);
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setCreated()
    {
        Date creation = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        created = dateFormat.format(creation);
        System.out.println(dateFormat.format(creation));
    }

    public void setUserID(int id)
    {
        userID = id;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String [] getAll()
    {
        String [] info = new String [4];

        info[0] = username;
        info[1] = password;
        info[2] = firstName;
        info[3] = lastName;

        return info;
    }

    public String getUsername()
    {
        return username;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public int    getUserID()
    {
        return userID;
    }

    public String getCreation()
    {
        return created;
    }

    public String getStatus()
    {
        return status;
    }
}
