package sample;

public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    User(String username, String password, String firstName, String lastName)
    {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);

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


}
