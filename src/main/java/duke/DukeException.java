package duke;

/**
 * Class for duke-specific exceptions.
 */
class DukeException extends Exception {
    /**
     * Constructor of duke-specific exception.
     *
     * @param s string input of exception.
     */
    DukeException(String s) {
        super(s);
    }
}
