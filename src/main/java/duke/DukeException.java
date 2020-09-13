package duke;

/**
 * Class for duke-specific exceptions.
 */
class DukeException extends Exception {
    /**
     * Constructs duke-specific exception.
     *
     * @param s String input of exception.
     */
    DukeException(String s) {
        super(s);
    }
}
