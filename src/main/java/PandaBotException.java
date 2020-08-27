/**
 *  Represents a general exception thrown by PandaBot.
 */
public class PandaBotException extends Exception {

    /**
     * Creates a PandaBotException object.
     * 
     * @param msg the error message to show
     */
    public PandaBotException(String msg) {
        super(msg);
    }
}
