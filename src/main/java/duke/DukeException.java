package duke;

/**
 * Represents Exception thrown within Duke.
 */
public class DukeException extends Exception {

    String message;

    public DukeException(String input) {
        super(input);
        message = input;
    }

    @Override
    public String toString() {
        return "Apple Pineapple!! " + message;
    }
}
