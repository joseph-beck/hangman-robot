package client;

import java.io.IOException;
import java.net.Socket;

import robot.States;

/**
 * The SingleClient acts as a singleton that interacts with a single instance of a Client objcet.
 * This is useful for the behaviors as it meanas clients do not to be closed and opened for each behavior.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */

public final class SingleClient {
    
    /**
     * Stores this instance of this singleton.
     */
    private static SingleClient instance;
    /**
     * Stores the instance of the Client object.
     */
    private static Client client;
    /**
     * Stores the username of the client.
     */
    private static String username;


    /**
     * Initializes the username, socket and client used by the singleton.
     * 
     * @exception IOException
     * @return none
     */
    private SingleClient() {

    }

    public static void initialize() {
        try {
            username = "robot";
            Config.getInstance();
            Socket socket = new Socket(Config.getAddress(), Config.getPort());
            client = new Client(socket, username);
            client.connect();
            client.sendJoin();
            client.listenForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Returns the instance of this client and initialzies it if it is null.
     * 
     * @return SingleClient
     */
    public static SingleClient getInstance() {
        if (instance == null) instance = new SingleClient();

        return instance;
    }

    /**
     * Returns whether or not the client is currently conneted.
     * 
     * @return boolean
     */
    public static boolean clientConnected() {
        return client.isConnected();
    }
    
    /**
     * Sends a packet via the clients sendPacket() method.
     * 
     * @param Packet packet
     * @return none
     */

    public static void sendPacket(Packet packet) {
        client.sendPacket(packet);
    }

    /**
     * Sends a piece of data that is passed as a string.
     * A packet is constructed from the string and then sent via the client.
     * 
     * @param String data
     */
    public static void sendDataPacket(String data) {
        Packet packet = new Packet(
            username,
            "all",
            "data",
            data
        );

        client.sendPacket(packet);
    }

    /**
     * Sends a state via a packet which is constructed and sent via the client.
     * 
     * @param States state
     * @return none
     */
    public static void sendStatePacket(States state) {
        Packet packet = new Packet(
            username,
            "all",
            "state",
            state.name()
        );

        client.sendPacket(packet);
    }

    /**
     * Returns the latest packet from the server.
     * 
     * @return Packet
     */
    public static Packet getLatestPacket() {
        return client.getLatestPacket();
    }

    /**
     * Sets the value of the latest packet from the server.
     * 
     * @param Packet
     * @return none
     */
    public static void setLatestPacket(Packet packet) {
        client.setLatestPacket(packet);
    }

    /**
     * Gets a packet from the server.
     * 
     * @return Packet
     */
    public static Packet getPacket() {
        return client.getPacket();
    }
}