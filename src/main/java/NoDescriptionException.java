/**
 * The exception that is thrown when the user provides no description for a task.
 */
public class NoDescriptionException extends DukeException{
    public NoDescriptionException(String taskIdentifier) {
        super("Eh you never tell me the description of " + taskIdentifier +
                " how you want me to record sia?");
    }
}
