package duke.utils;

public class DukeException extends Exception {

    /**
     * Creates a custom exception
     *
     * @param msg The error encountered
     */
    public DukeException(String msg) {
        super("OOPS! " + msg);
    }

}