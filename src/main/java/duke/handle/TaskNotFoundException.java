package main.java.duke.handle;

/**
 * The TaskNotFoundException class describes the exception where
 * the task specified by the command cannot be found in the task list.
 */
public class TaskNotFoundException extends Exception {

    /**
     * Takes in the message of why the task cannot be found and
     * returns an exception.
     *
     * @param message The message of why the task cannot be found.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}
