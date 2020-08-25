package main.java.duke;

public class DukeException extends Exception{

    /**
     * Creates an instance of DukeException, a custom class for exceptions found within the Duke application
     * @param error the strign representation of the error
     */
    public DukeException(String error) {
        super(error);
    }

}
