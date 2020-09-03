package duke;

/**
 * CommandNotRecognizedException is thrown when the input is not recognized.
 */
public class CommandNotRecognizedException extends Exception {
    
    /**
     * Formats the string of CommandNotRecognizedException.
     * 
     * @param message Takes in the error message to be printed.
     */
    public CommandNotRecognizedException(String message) {
        super(message);
    }
    
}
