/**
 * Represents a type of PandaBotException which is thrown when there is an error in
 * loading a task from the save file.
 */
public class PandaBotLoadingTasksErrorException extends PandaBotException {

    /**
     * Creates a new PandaBotLoadingTasksErrorException object which
     * is used to show that there is an error with loading a task from the save file. 
     */
    public PandaBotLoadingTasksErrorException(String msg) {
        super("Error in loading the task: " + msg);
    }
}
