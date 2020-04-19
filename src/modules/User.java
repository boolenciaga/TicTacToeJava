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

    public User(){}
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

    public void getAll() {
        System.out.println(getUsername());
        System.out.println(getFirstName());
        System.out.println(getLastName());
        System.out.println(getPassword());
        System.out.println(getStatus());
        System.out.println(getUserID());
    }
}
