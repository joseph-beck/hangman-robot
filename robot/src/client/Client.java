package client;

import java.util.Scanner;
import java.net.*;
import java.io.*;
import jade.*;

/**
 * The client class handles all IO interactions with the server.
 * This is done through a TCP Socket.
 * To ensure constistency between data packets Jade has also been implemented.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */

public class Client {

    /**
     * Stores the socket passed from the Connect class.
     */
    private Socket socket;
    /**
     * Stores the username passed from the Connect class.
     */
    private String username;
    /**
     * Stores the last message recieved by the client.
     */
    private Packet latestPacket;
    /**
     * Stores the buffered reader which takes an input stream from the Socket.
     */
    private BufferedReader bufferedReader;
    /**
     * Stores the buffered writer which outputs to the Socket.
     */
    private BufferedWriter bufferedWriter;

    /**
     * Stores the jadeSerializer for the client class.
     */
    private JadeSerializer jadeSerializer;
    /**
     * Stores the jadeDeserializer for the client class.
     */
    private JadeDeserializer jadeDeserializer;

    /**
     * The Client constructor takes in the below params and then initializes the buffered reader and writer.
     * The JadeSerializer and JadeDeserializer are also initiliazed here.
     * 
     * @param Socket socket
     * @param String username
     * @exception IOException
     * @return none
     */
    public Client(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
    }

    /**
     * Attempts to connect to the server.
     * 
     * @exception IOException
     * @return none
     */
    public void connect() {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.jadeSerializer = new JadeSerializer();
            this.jadeDeserializer = new JadeDeserializer();
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Sends a join message to the server (just username).
     * 
     * @exception IOException
     * @return none
     */
    public void sendJoin() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            close();
        }
    }

    /**
     * The sendMessage method uses the console to take in messages from the user.
     * Also sends the username of the username at the first to get initialized as a user on the server.
     * 
     * 
     * @exception IOException
     * @return none
     */
    public void sendJoinMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sendMessage();
        } catch (IOException e) {
            close();
        }
    }

    /**
     * The send message function sends a message to the server.
     * The user's message is first converted into a Packet then JadeObject then a serialized string.
     * The serialized string is then sent using the bufferedWriter.
     * Also note the first message sent from the bufferedWriter is the clients username, this is so the user can be initialized server side.
     * 
     * @throws IOException
     * @return none
     */
    public void sendMessage() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                Packet packet = new Packet(username, "all", "data", message);
                JadeObject jadeObject = packet.toJadeObject();
                String messageToSend = jadeSerializer.serializeObjectToString(jadeObject);

                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            scanner.close();
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Sends a packet to the server through the buffered writer.
     * Also sends the username so the client can be initalized on the server.
     * 
     * @param Packet packet
     * @exception IOException
     * @return none
     */
    public void sendJoinPacket(Packet packet) {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sendPacket(packet);
        } catch (IOException e) {
            close();
        }
    }
    /**
     * Sends a packet to the server.
     * Similarly to the sendMessage the packet is converted to a JadeObject then serialized to a string.
     * 
     * @exception IOException
     * @param Packet packet
     * @return none
     */
    public void sendPacket(Packet packet) {
        try {
            if (socket.isConnected()) {
                JadeObject jadeObject = packet.toJadeObject();
                String messageToSend = jadeSerializer.serializeObjectToString(jadeObject);

                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Creates a new runnable thread and starts it in order to listen for messages from the server.
     * Threaded so that the Client can both send and recieve simultaneously.
     * Gets a packet from the getPacket() method and assigns latest packet based on its contents.
     * 
     * @exception IOException
     * @return none
     */
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    Packet packet = getPacket();
                    if (packet != null) latestPacket = packet;
                }
            }
        }).start();
    }

    /**
     * Gets a packet from the server.
     * 
     * @exception IOException
     * @return Packet
     */
    public Packet getPacket() {
        if (!socket.isConnected()) close();

        try {
            String message = bufferedReader.readLine();
            JadeObject jadeMessage = jadeDeserializer.deserializeFromString(message);
            return jadeMessage.toPacket();
        } catch (IOException e) {
            close();
        }
        return null;
    }

    /**
     * Close the socket as well as the reader and writers interacting with it.
     * Then prints the stack trace.
     * 
     * @exception IOException
     * @return none
     */
    public void close() {
        try {
            if (socket != null) socket.close();
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the server is connected or not.
     * 
     * @exception IOException
     * @return boolean
     */
    public boolean isConnected() { 
        return this.socket.isConnected(); 
    }

    /**
     * Returns the latest packet from the server that is not null.
     * 
     * @return Packet
     */

    public Packet getLatestPacket() { 
        return this.latestPacket; 
    }

    /**
     * Sets the latest packet of the client.
     * 
     * @param Packet packet
     */
    public void setLatestPacket(Packet packet) {
        this.latestPacket = packet;
    }
}