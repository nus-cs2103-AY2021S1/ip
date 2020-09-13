/**
 * Represents a Ui to print messages to interact with User.
 */
public class Ui {

    /**
     * Prints out the message with an added signature of mochi the penguin.
     *
     * @param msg Original message to be displayed.
     * @return A nicely formatted message with a signature from mochi the penguin.
     */
    public static String messagePrint(String msg) {
        return msg + "\n ~mochi the penguin";
    }
}
