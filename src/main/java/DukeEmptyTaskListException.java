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
