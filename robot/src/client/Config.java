package client;

/**
 * Stores configs for connecting the client
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public final class Config {
    
    /**
     * Stores the instance of this Config.
     */
    private static Config config;
    /**
     * Stores the address of the server.
     */
    private static String address;
    /**
     * Stores the port of the server.
     */
    private static int port;
    
    /**
     * Config constructor initializes the values of both ip and port.
     * 
     * @return none
     */
    private Config() {
        address = "10.0.1.4";
        port = 6244;
    }

    /**
     * Gets an instance of the static Config object, if it is null then creates one.
     * 
     * @return Config
     */
    public static Config getInstance() {
        if (config == null) config = new Config();

        return config;
    }

    /**
     * Return the value in address.
     * 
     * @return String
     */
    public static String getAddress() {
        return address;
    }

    /**
     * Returns the value in port.
     * 
     * @return int
     */
    public static int getPort() {
        return port;
    }
}