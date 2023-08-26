package robot;

/**
 * Stores the current state the robot and handles some of its behavior.
 *
 * @author Joseph Beck
 * @version 0.1
 * @since 2023
 */
public final class StateHandler {

    /**
     * Stores the instance of this StateHandler.
     */
    private static StateHandler instance;
    /**
     * Stores the current state of the robot.
     */
    private static States state;

    /**
     * When initialized state is set to WELOME.
     */
    public StateHandler() {
        state = States.WELCOME;
    }

    /**
	 * Gets an instance of StateHandler and returns it.
	 * If it is null then an instace of it is created.
	 * 
	 * @return StateHanler
	 */
    public static StateHandler getInstance() {
        if (instance == null) instance = new StateHandler();

        return instance;
    }

    /**
     * Sets the current state.
     * 
     * @param States new state
     */
    public static void setState(States newState) {
        state = newState;
    }

    /**
     * Return the current state
     * 
     * @return States
     */
    public static States getState() { 
        return state; 
    }
}