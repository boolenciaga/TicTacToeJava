package sample;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class User {

    private int    userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date   created;
    private String status;

    User(String username, String password, String firstName, String lastName, Date created)
    {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setCreated(created);
        setStatus("OFFLINE");

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

    public void setCreated(Date creation)
    {
        this.created = creation;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(created);
        return strDate;
    }

    public String getStatus()
    {
        return status;
    }
}
