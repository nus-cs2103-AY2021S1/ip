package main.java.duke;

public class DukeException extends Exception{

    /**
     * Default constructor for DukeException containing a generic error message;
     */
    public DukeException() {
        super("  \u2639 OOPS!!! I'm sorry, but I don't know what " +
                "that means :-(");
    }
    /**
     * Creates an instance of DukeException, a custom class for exceptions found within the Duke application
     * @param error the string representation of the error
     */
    public DukeException(String error) {
        super(error);
    }

}
