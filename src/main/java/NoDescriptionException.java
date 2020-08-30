/**
 * The exception that is thrown when the user provides no description for a task.
 */
public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String taskIdentifier) {
        super("Can you tell me the description of " + taskIdentifier
                + " properly? Well, I was a factory rejected toy after all :( but my creator loves me a lot!");
    }
}
