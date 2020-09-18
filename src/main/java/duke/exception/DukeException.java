package duke.exception;

import duke.tool.Emoji;
import duke.ui.Ui;

/**
 * Represents the exception thrown by duke.Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a duke.Duke exception.
     *
     * @param message message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Outputs exception as a string.
     *
     * @return string representation of the exception.
     */
    @Override
    public String toString() {
        String emoji = Emoji.ERROR.toString();
        return Ui.SEPARATION_LINE
                + "    " + emoji + this.getMessage() + "\n"
                + Ui.SEPARATION_LINE;
    }
}
