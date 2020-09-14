/**
 * Thrown to indicate that the user has requested to display the list when there is no tasks.
 */
public class DukeEmptyTaskListException extends DukeException {

    /**
     * Constructs a DukeEmptyEventTimeException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyTaskListException(String s) {
        super("OOPS!!! There are no tasks entered!.");
    }
}
