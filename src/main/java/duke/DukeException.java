package duke;

/**
 * Represents Exception thrown within Duke.
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Creates DukeExcpetion object.
     * @param input Custom message to be printed in DukeException message.
     */
    public DukeException(String input) {
        super(input);
        message = input;
    }

    @Override
    public String toString() {
        return "Apple Pineapple!! " + message;
    }
}
