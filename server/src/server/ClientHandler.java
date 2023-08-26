package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * The ClientHandler object implements the Runnable abstract class.
 * This is done in order to provide a separate runnable thread for each user that joins the server.
 * This improves performance and also allows for greater flexibility.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public class ClientHandler implements Runnable {

    /**
     * Socket that the server is hosted on.
     */
    private Socket socket;
    /**
     * Stores the reader of the socket.
     */
    private BufferedReader bufferedReader;
    /**
     * Stores the writer of the socket.
     */
    private BufferedWriter bufferedWriter;
    /**
     * Name of connected client.
     */
    private String clientUsername;

    /**
     * Constructor of the ClientHandler object takes in the socket.
     * 
     * @param Socket socket
     * @exception IOException
     * @return none
     */
    public ClientHandler(Socket socket) {
        try {
            Handler.getInstance();

            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine(); // Username is given to the server upon connection

            Handler.addHandler(this);
            String message = String.format(
                "sender : '%s';reciever : 'all';type : 'join';data : 'void';checkSum : '1';",
                clientUsername
            );
            broadcastMessage(message);
        } catch (IOException e) {
            close();
        }
    }

    /**
     * The removeClientHandler method removes a given client from the server and the list of ClientHandlers.
     * 
     * @return none
     */
    public void removeClientHandler() {
        Handler.removeHandler(this);
        String message = String.format(
            "sender : '%s';reciever : 'all';type : 'leave';data : 'void';checkSum : '1';",
            clientUsername
        );
        broadcastMessage(message);
    }

    /**
     * The broadcastMessage method sends a message to every connected client of the server, including the one that sent it.
     * This is mainly used when users leave and join.
     * 
     * @param String message
     * @exception IOException
     * @return none
     */
    public void broadcastMessage(String message) {
        for (ClientHandler clientHandler : Handler.getHandlers()) {
            try {
                writeMessage(clientHandler, message);
            } catch (IOException e) {
                close(e);
            }
        }
    }

    /**
     * The sendMessage method sends a message to everyone but the client that sent it
     * This is for more common "chatroom" like interactions.
     * 
     * @param String message
     * @exception IOException
     * @return none
     */
    public void sendMessage(String message) {
        for (ClientHandler clientHandler : Handler.getHandlers()) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    writeMessage(clientHandler, message);
                }
            } catch (IOException e) {
                close(e);
            }
        }
    }

    /**
     * This overloaded version of sendMessage allows a specific user to be targeted.
     * The targeted user is the only one that will see the message.
     * 
     * @param String message
     * @param String targetUser
     * @exception IOException
     * @return none
     */
    public void sendMessage(String message, String targetUser) {
        for (ClientHandler clientHandler : Handler.getHandlers()) {
            try {
                if (clientHandler.clientUsername.equals(targetUser)) {
                    writeMessage(clientHandler, message);
                }
            } catch (IOException e) {
                close(e);
            }
        }
    }

    /**
     * Writes a message to the a specified client via the bufferedWriter.
     * It then adds a new line and flushes the buffer.
     * 
     * @param clientHandler
     * @param message
     * @throws IOException
     * @return none
     */

    public void writeMessage(ClientHandler clientHandler, String message) throws IOException {
        clientHandler.bufferedWriter.write(message);
        clientHandler.bufferedWriter.newLine();
        clientHandler.bufferedWriter.flush();
    }

    /**
     * Implementing the run function from the abstract method runnable.
     * While socket is connected it takes in a message from the reader and writes it to all clients, except the sender.
     * 
     * @param none
     * @exception IOException
     * @return none
     */
    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                sendMessage(messageFromClient);
            } catch (IOException e) {
                close(e);
                break;
            }
        }
    }

    /**
     * The close method first removes the client handler and then tries to close all resources used by that client.
     * To prevent trying to close a null object each are checked before closing.
     * 
     * @exception IOException
     * @return none
     */
    public void close() {
        removeClientHandler();
        try {
            if (socket != null) socket.close();
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The close method first removes the client handler and then tries to close all resources used by that client.
     * To prevent trying to close a null object each are checked before closing.
     * This version of close also takes in a parameter of IOException in order to print out the error before closing the resources.
     * 
     * @param IOException
     * @exception IOException
     * @return none
     */
    public void close(IOException e) {
        System.out.println("Error occurred: ");
        e.printStackTrace();
        close();
    }

    /**
     * Gets the client username
     * 
     * @return String
     */
    public String getClientUsername() {
        return this.clientUsername;
    }

    /**
     * Sets the client username
     * 
     * @param String clientUsername
     * @return none
     */
    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}