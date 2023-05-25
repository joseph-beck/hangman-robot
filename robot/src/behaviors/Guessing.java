package behaviors;

import client.Packet;
import client.SingleClient;
import lejos.robotics.subsumption.Behavior;
import robot.StateHandler;
import robot.States;

/**
 * Waits for the user to guess, when the guess switches to drawing and draws either hangman or (a) character(s) based on the guess.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Guessing implements Behavior {

    /**
     * Stores a boolean for whether the behavior should be surpressed.
     */
    private boolean suppressed;

    /**
     * Guessing constructor assigns surpressed to false.
     * 
     * @return none
     */
    public Guessing() {
        this.suppressed = false;
    }

    /**
     * Action method makes sure surpessed is false before starting and then sends a state packet to the server.
     * Then it gets a packet from the server and gets the data, the data when type is guess will be the guess.
     */
    @Override
    public void action() {
        suppressed = false;
        SingleClient.sendStatePacket(StateHandler.getState());

        while (!suppressed) {
            Packet packet = SingleClient.getLatestPacket();

            if (packet != null) { // Checks for null packet before using
                if (packet.getType().equals("guess") && packet.getData().length() == 1) {
                    BehaviorData.setCurrentGuess(packet.getData().toUpperCase().charAt(0));
                    StateHandler.setState(States.DRAWING);
                }
            }
        }
    }

    /**
     * Uses surpress to exit the loop at times.
     * 
     * @return none
     */
    @Override
    public void suppress() {
        suppressed = true;
    }

    /**
     * Takes control when the state is equal to the GUESSING state.
     * 
     * @return boolean
     */
    @Override
    public boolean takeControl() {
        return StateHandler.getState() == States.GUESSING;
    }
}