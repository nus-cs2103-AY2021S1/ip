/**
 * Represents a type of PandaBotException which is thrown when a value is exceeds
 * the range of values available.
 */
public class PandaBotOutOfRangeException extends PandaBotException{

    /**
     * Creates a new PandaBotOutOfRangeException object which
     * is used to show that there a value exceeded the range available.
     */
    public PandaBotOutOfRangeException() {
        super("Task number given is not in range. Which task are you referring to?");
    }
}
