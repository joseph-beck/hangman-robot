package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Starts the server.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public final class ServerRunner {

    /**
     * A static method that runs the Server.
     * 
     * @throws IOException
     * @exception IOException
     * @return none
     */
    public static void Run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6244);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}