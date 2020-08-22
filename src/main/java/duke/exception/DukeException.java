package duke.exception;

/**
 * An exception to be thrown in the Duke application when illegal commands or exceptions are met.
 */
public class DukeException extends Exception {
    String line = "___________________________________________________________________________________";
    String smallSpace = "    ";
    String bigSpace = "     ";

    /**
     * Constructs the DukeException.
     *
     * @param message The error message to be .
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Represents the exception message in the personalized format.
     *
     * @return a string that represents the exception message.
     */
    public String toString() {
        return smallSpace + line + "\n" + bigSpace + "☹ OOPS!!! " +
                this.getMessage() + "\n" + smallSpace + line + "\n";
    }
}
