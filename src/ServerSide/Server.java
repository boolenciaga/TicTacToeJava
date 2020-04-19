package ServerSide;

import Messages.LoginMsg;
import Messages.Message;
import Messages.RegistrationMsg;
import sqlite.DatabaseManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Server
{
    public static void main(String[] args)
    {
        new Server();
    }

    // DATA MEMBERS

    //the port this ServerManager is on
    private final int serverPort = 7777;

    //an ArrayList of the different ClientManagers connected to this server
    private HashMap<UUID, ClientConnection> clientMap = new HashMap<>();

    //a blocking queue for messages to be processed
    private ArrayBlockingQueue<Message> msgQueue = new ArrayBlockingQueue<>(1000);

//    //a map which holds the currently existing chat rooms
//    private HashMap<String, ChatRoomServer> chatRoomMap = new HashMap<>();

    // METHODS

    private Server()
    {
        System.out.println("Server turned on\n");

        try
        {
            //start the Publisher
            new Thread(new Publisher()).start();

            //establish server on a port
            ServerSocket ss = new ServerSocket(serverPort);

            //ServerManager open for connections to ClientManagers
            while(true)
            {
                Socket socket = ss.accept(); //connections to cMs

                if(socket.isConnected())
                {
                    System.out.println("Server connected to a client");
                    System.out.println(socket.getInetAddress().getHostAddress() + " (port " + socket.getPort() + ")\n");

                    //create connection id and store connection
                    UUID id = UUID.randomUUID();
                    clientMap.put(id, new ClientConnection(socket, id));
                }
                else
                    System.out.println("Server didn't connect !!!\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class ClientConnection implements Runnable
    {
        //DATA MEMBERS
        private Socket socket;
        private UUID connectionID; //used to identify this ClientConnection
        private String clientsUserName;
        private Thread clientThread;

        //this connection's socket streams
        private ObjectInputStream objectInputFromClientManager;
        private ObjectOutputStream objectOutputToClientManager;

        //METHODS
        ClientConnection(Socket socket, UUID id)
        {
            this.socket = socket;
            connectionID = id;

            wrapSocketStreams();

            //start this object's thread
            clientThread = new Thread(this);
            clientThread.start();
        }

        @Override
        public void run()
        {
            try
            {
                //process chat room requests
                while(true)
                {
                    Message incomingMsg = (Message) objectInputFromClientManager.readObject();
                    incomingMsg.setConnectionID(connectionID);
                    msgQueue.add(incomingMsg);
                }
            }
            catch (IOException | ClassNotFoundException e)
            {
                //TO DO: if caught here likely client closed program. kill this client connection
                System.out.println("exception caught in one of server's ClientConnection object's run()");
                e.printStackTrace();
            }
        }

        void sendMessageFromPublisher(Message msg)
        {
            try
            {
                objectOutputToClientManager.writeObject(msg);
            }
            catch (IOException e) {
//                System.out.println("exception caught in sendMessageFromPub() of " + memberName + "'s MemberConnection object");
                e.printStackTrace();
            }
        }

        private void wrapSocketStreams()
        {
            try {
                objectOutputToClientManager = new ObjectOutputStream(socket.getOutputStream());
                objectInputFromClientManager = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.out.println("exception caught in wrapSocketStream()\n\n");
                e.printStackTrace();
            }
        }
    }


    /*********************************************************************************/


    private class Publisher implements Runnable
    {
        @Override
        public void run()
        {
            while(true)
            {
                try
                {
                    //pull message off queue
                    Message nextMsg = msgQueue.take();

                    //process the message
                    if(nextMsg instanceof RegistrationMsg)
                    {
                        RegistrationMsg regMsg = (RegistrationMsg) nextMsg;

                        Object success = DatabaseManager.getInstance().insert(regMsg.getUser());

                        ClientConnection client = clientMap.get(regMsg.getConnectionID());

                        if(success == null)
                            client.objectOutputToClientManager.writeBoolean(true);
                        else
                            client.objectOutputToClientManager.writeBoolean(false);

                        client.objectOutputToClientManager.flush();
                    }
                    else if(nextMsg instanceof LoginMsg)
                    {
                        LoginMsg loginMsg = (LoginMsg) nextMsg;

                        Object returnStatus = DatabaseManager.getInstance().authenticate(loginMsg.getUsername(), loginMsg.getPassword());
                        ClientConnection client = clientMap.get(loginMsg.getConnectionID());

                        if(returnStatus != null)
                            client.objectOutputToClientManager.writeBoolean(true);
                        else
                            client.objectOutputToClientManager.writeBoolean(false);

                        client.objectOutputToClientManager.flush();
                    }
                }
                catch (InterruptedException | IOException e) {
                    System.out.println("exception caught in server Publisher's run()");
                    e.printStackTrace();
                }
            }
        }
    }
}
