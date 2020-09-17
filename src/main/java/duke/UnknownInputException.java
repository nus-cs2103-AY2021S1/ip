package duke;

/**
 * Creates an exception to be thrown when the input is unknown.
 */
public class UnknownInputException extends Exception {
    public UnknownInputException() {
        super("\n     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
