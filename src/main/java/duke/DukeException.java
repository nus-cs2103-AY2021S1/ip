package duke;

/**
 * DukeException is a subclass of Exception that handles all the Duke exceptions.
 */

public class DukeException extends Exception {
    private String msg;

    /**
     * Constructor for a duke exception.
     * @param msg a text to be printed out.
     */
    public DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        if (msg.equals("file not found")) {
            return "no database found! pls try again ^__^";
        } else if (msg.equals("command not found")) {
            return "Meimei doesn't understand this command!";
        } else if (msg.equals("time")) {
            return "Date format error";
        } else {
            return "☹ OOPS!!! The description of a " + msg + " cannot be empty.";
        }
    }
}
