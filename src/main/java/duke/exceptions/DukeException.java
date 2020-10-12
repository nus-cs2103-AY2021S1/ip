package duke.exceptions;

/**
 * Represents a DukeException.
 */
public class DukeException extends Exception {

    /**
     * Returns the prefix to all Duke exception messages.
     * @return Prefix to Duke exception message.
     */
    @Override
    public String toString() {
        return "      ☹ OOPS!!!";
    }
}
