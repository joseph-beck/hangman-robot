package server;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler holds a list of clients.
 *  
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public final class Handler {
    
    /**
     * Stores an instance of the Handler object.
     */
    private static Handler instance;
    /**
     * Stores the handlers for the clients.
     */
    private static List<ClientHandler> handlers; 
    
    /**
     * Initializes the handlers to a new array list.
     * 
     * @return none
     */
    private Handler() {
        handlers = new ArrayList<ClientHandler>(); 
    }

    /**
     * Gets the instance of the Handler.
     * If it is null a new instance will be created.
     * 
     * @return Handler
     */
    public static Handler getInstance() {
        if (instance == null) instance = new Handler();

        return instance;
    }

    /**
     * Returns the the client handlers.
     * 
     * @return none
     */
    public static List<ClientHandler> getHandlers() {
        return handlers;
    }

    /**
     * Adds a client to the handlers.
     * 
     * @param ClientHandler client
     * @return none
     */
    public static void addHandler(ClientHandler client) {
        handlers.add(client);
    }
    
    /**
     * Remove a client from handlers.
     * 
     * @param ClientHandler client
     * @return none
     */
    public static void removeHandler(ClientHandler client) {
        handlers.remove(client);
    }

    /**
     * Prints out a list of all connected clients.
     * 
     * @return none
     */
    public static void listHandlers() {
        for (ClientHandler client : handlers) {  
            if (client != null) System.out.println(client.getClientUsername());
        }
    }
}