package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The connect class establishes a connection, via a TCP Socket, to the server and initliazes a client.
 * This client is then able to both send and recieve messages from the TCP server.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */

public class Connect {

    /**
     * Stores the name of the user.
     */
    private String name;
    /**
     * Stores the ip of the server.
     */
    private String ip;
    /**
     * Stores the port of the server.
     */
    private int port;

    /**
     * The constructor of the Connect object takes in 3 parameters and assigns them.
     * 
     * @param String name
     * @param String ip
     * @param int port
     * @return none
     */
    public Connect(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    /**
     * The startConnection method uses the ip, port and username given from the constructor.
     * These are used within the creation of a new Client and Socket object.
     * These are then sent to listen and send messages.
     * 
     * @exception UnknownHostException, IOException
     * @return none
     */
    public void startConnection() {
        try {
            Socket socket = new Socket(ip, port);
            Client client = new Client(socket, name);

            client.listenForMessage();
            client.sendPacket(new Packet("robot", "all", "data", "c"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}