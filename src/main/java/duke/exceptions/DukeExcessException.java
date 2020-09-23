package duke.exceptions;

public class DukeExcessException extends DukeException {

    /**
     * Class constructor.
     *
     */
    public DukeExcessException() {
        super("Excessive tasks (more than 100) stored in list - Delete some completed/redundant tasks!");
    }
}
