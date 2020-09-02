package main.java.emily.command;

/**
 * Alerts the program if logic fails.
 */
public class DukeException extends Exception {

    public DukeException(String error) {
        super(error);
    }

}

