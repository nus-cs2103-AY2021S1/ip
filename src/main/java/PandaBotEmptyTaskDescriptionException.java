/**
 * Represents a type of PandaBotException which is thrown when the task description is empty.
 */
public class PandaBotEmptyTaskDescriptionException extends PandaBotException{

    /**
     * Creates a PandaBotEmptyTaskDescriptionException object which is used to
     * show that the description of the task is empty.
     * 
     * @param taskName the name of the task that has an empty description
     */
    public PandaBotEmptyTaskDescriptionException(String taskName) {
        super(String.format(":c OOPS! The description of the %s cannot be empty.", taskName));
    }
}
