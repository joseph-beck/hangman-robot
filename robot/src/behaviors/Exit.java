package behaviors;

import lejos.robotics.subsumption.Behavior;
import robot.StateHandler;
import robot.States;

/**
 * Underlying behavior in order to stop the arbitrator to exit when changing state, returns true unless within the exit state.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Exit implements Behavior {
    
    /**
     * Empty constructor for Exit behavior
     * 
     * @return none
     */
    public Exit() { }

    /**
     * Action does nothing in Exit.
     * 
     * @return none
     */
    @Override
    public void action() { }

    /**
     * Supress does nothing in Exit.
     */
    @Override
    public void suppress() { }

    /**
     * Takes control if not in the EXIT state and BehaviorData's exiting value is false.
     */
    @Override
    public boolean takeControl() {
        return StateHandler.getState() != States.EXIT && !BehaviorData.getExiting();
    }
}