package duke.component;

public class DukeException extends Exception {

    /**
     * Returns a standard Duke exception
     * @param errorMessage string that gets printed after error message
     */
    public DukeException(String errorMessage) {
        super("☹ OH NO!!! " + errorMessage);
    }
}