/**
 * DukeDuplicateException throws an exception when the input task is already in the task list.
 */
public class DukeDuplicateException extends DukeException {

    /**
     * Throws an exception with a message when there is a duplicate task.
     * @param message exception message
     */
    public DukeDuplicateException(String message) {
        super(message);
    }
}
