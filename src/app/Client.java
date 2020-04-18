package app;

import modules.User;

public class Client {

    static User client = new User("","","","");

    static void update(String username, String password, String firstName, String lastName, int id)
    {
        client.setUsername(username);
        client.setPassword(password);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setUserID(id);

    }

    static void login()
    {
        client.setStatus("ONLINE");
    }

    static void logoff()
    {
        client.setStatus("OFFLINE");
    }

    static void delete()
    {
        client.setStatus("INACTIVE");
    }

    static void display()
    {
        client.getAll();
    }

}
