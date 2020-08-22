package main.java.duke.handle;

/**
 * The CommandNotFoundException class describes the exception where
 * the command entered by the user cannot be parsed.
 */
public class CommandNotFoundException extends Exception {

    /**
     * Takes in a message regarding the reason why
     * the command cannot be parsed and returns an exception.
     *
     * @param message The message of why the command cannot be parsed.
     */
    public CommandNotFoundException(String message) {
        super(message);   
    }
}
