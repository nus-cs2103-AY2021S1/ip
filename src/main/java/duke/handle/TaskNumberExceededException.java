package main.java.duke.handle;

/**
 * The TaskNumberExceededException class describes the exception where
 * the number of tasks in the task list has reached the limit and no more
 * tasks can be added to the task list.
 *
 */
public class TaskNumberExceededException extends Exception {

    /**
     * Takes in the message of why the task cannot be added into
     * the task list and returns an exception.
     *
     * @param message The message of why the task cannot be added to the task list.
     */
    public TaskNumberExceededException(String message) {
        super(message);
    }
}
