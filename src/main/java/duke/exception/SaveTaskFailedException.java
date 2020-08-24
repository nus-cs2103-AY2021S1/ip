package duke.exception;

/**
 * The Save task failed exception.
 */
public class SaveTaskFailedException extends SaveFailedException {
    /**
     * Instantiates a new Save task failed exception.
     *
     * @param taskIndex the task index.
     */
    public SaveTaskFailedException(int taskIndex) {
        super("task " + taskIndex);
    }
}
