package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class is used to start a TCP Server as well as initialize the ClientHandler.
 * To ensure the Server can handle multiple clients at the same threads are used for each user.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public class Server {

    /**
     * Stores the servers socket.
     */
    private ServerSocket serverSocket;

    /**
     * Constructor of the server, simply assigns class serverSocket to the passed serverSocket.
     * @param ServerSocket serverSocket
     * @return none
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Constructor of the server, creates a new instance of a ServerSocket object with the given port.
     * The ServerSocket is assigned to the classes serverSocket.
     * 
     * @param int port
     * @return none
     */
    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The startServer method starts the server and initializes the clientHandler with the socket.
     * The thread is then started.
     * 
     * @exception IOException
     * @return none
     */
    public void startServer() {
        System.out.println("Starting server");
        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");

                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket(e);
        }
    }

    /**
     * The closeServerSocket method tries to close the serverSocket as long as it is not null.
     * 
     * @exception IOException
     * @return none
     */
    public void closeServerSocket() {
        try {
            System.out.println("Closing server socket");
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    /**
     * The closeServerSocket method closes the server socket via closeServerSocket().
     * Also outputs the error that occurred.
     * 
     * @param IOException
     * @exception IOException
     * @return none
     */
    public void closeServerSocket(IOException e) {
        System.out.println("Error occured: ");
        e.printStackTrace();
        closeServerSocket();
    }
}