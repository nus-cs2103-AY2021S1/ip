package src.main.java.duke.data.exception;

/**
 * Represents customised exception class for Bot.
 *
 */
public class DukeException extends Exception {
    private final String errorMesage;
    public DukeException(String errorMessage) {
        this.errorMesage = errorMessage;
    }

    public String toString() {
        return "☹ OOPS!!! " + errorMesage;
    }
}
