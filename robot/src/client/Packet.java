package client;

import jade.JadeObject;
import jade.JadePair;

/**
 * The Packet class holds the Packet object which is used commonly within the Client in order to send and recieve data.
 * It can be converted to and from a JadeObject in order to provide ease of use when trying to send structured data.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */

public class Packet {

    /**
     * Holds the name of the sender (this client).
     */
    private String sender;
    /**
     * Holds the name of the reciever (all if wanting the packet sent server wide).
     */
    private String reciever;
    /**
     * Stores the type of data the packet holds (join, leave, data, state).
     */
    private String type;
    /**
     * Stores the data of the packet.
     */
    private String data;
    /**
     * TODO: introduce correct check summing
     */
    private String checkSum;

    /**
     * Null packet constructor
     * 
     * @return none
     */
    public Packet() {
        this.sender = "";
        this.reciever = "";
        this.type = "";
        this.data = "";
        this.checkSum = "";
    }

    /**
     * Constructor of a packet with no checkSum.
     * checkSum is initialized to 1.
     * 
     * @param String sender
     * @param String reciever
     * @param String type
     * @param String data
     * @return none
     */
    public Packet(String sender, String reciever, String type, String data) {
        this.sender = sender;
        this.reciever = reciever;
        this.type = type;
        this.data = data;
        this.checkSum = "1";
    }

    /**
     * Constructor of a full packet.
     * 
     * @param String sender
     * @param String reciever
     * @param String type
     * @param String data
     * @param String checkSum
     * @return none
     */
    public Packet(String sender, String reciever, String type, String data, String checkSum) {
        this.sender = sender;
        this.reciever = reciever;
        this.type = type;
        this.data = data;
        this.checkSum = checkSum;
    }

    /**
     * Returns true if the packet has a valid checkSum for its contents.
     * TODO: implement fully
     * 
     * @return boolean
     */
    public boolean checkPacket() {
        return checkSum.equals("1"); 
    }

    /**
     * Converts the packet into a string array.
     * 
     * @return String array
     */
    public String[] toArray() {
        return new String[] { sender, reciever, type, data, checkSum };
    }

    /**
     * Converts a Packet object to a JadeObject.
     * This is done by creating a JadePair for each variable of the packet.
     * The JadePairs are then used to create a JadeObject which is then returned.
     * If the data of the packet is null a PacketConversionException is thrown.
     * 
     * @exception PacketConversionException
     * @return JadeObject
     */
    public JadeObject toJadeObject() {
        try {
            if (data.equals(null)) throw new PacketConversionException("null packet");
            
            JadePair senderPair = new JadePair("sender", sender);
            JadePair recieverPair = new JadePair("reciever", reciever);
            JadePair typePair = new JadePair("type", type);
            JadePair dataPair = new JadePair("data", data);
            JadePair checkSumPair = new JadePair("checkSum", checkSum);

            return new JadeObject(new JadePair[] {
                    senderPair, recieverPair, typePair, dataPair, checkSumPair
            });
        } catch (PacketConversionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts the packet to a string, this is an unstructured string.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format(
            "%s, %s, %s, %s, %s",
            sender,
            reciever,
            type,
            data,
            checkSum
        );
    }

    /**
     * Returns the sender of this.
     * 
     * @return String
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Sets the sender of this.
     * 
     * @return String
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Returns the reciever of this.
     * 
     * @return String
     */
    public String getReciever() {
        return this.reciever;
    }

    /**
     * Sets the reciever of this.
     * 
     * @return String
     */
    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    /**
     * Returns the type of this.
     * 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type of this.
     * 
     * @return String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the data of this.
     * 
     * @return String
     */
    public String getData() {
        return this.data;
    }

    /**
     * Sets the data of this.
     * 
     * @return String
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the check sum of this.
     * 
     * @return String
     */
    public String getCheckSum() {
        return this.checkSum;
    }

    /**
     * Sets the check sum of this.
     * 
     * @return String
     */
    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
}