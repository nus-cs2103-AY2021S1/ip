package luke.exception;

/**
 * Represents an exception thrown when a non-existing task is requested.
 */
public class LukeIndexOutOfBoundsException extends LukeException {
    /**
     * Creates a LukeIndexOutOfBoundsException object that indicates request for non-existing task.
     *
     * @param size size of the list
     */
    public LukeIndexOutOfBoundsException(String size) {
        super(String.format("The current task list contains less than %d number of tasks.\n"
                + "Please enter a valid task number.", size));
    }
}
