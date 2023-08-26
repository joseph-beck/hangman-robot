import robot.Robot;

/**
 *
 * @author Joseph Beck & Natan Wojcik
 * @version 0.1
 * @since 2023
 */
public class Main {

	/**
	 * Entry point of the program, runs the robot.
	 *
	 * @param String[] args
	 */
	public static void main(String[] args) {
		Robot robot = new Robot("0.1a");
		robot.run();
	}
}
