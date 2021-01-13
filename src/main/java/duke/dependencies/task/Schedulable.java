package duke.dependencies.task;

/**
 * An interface representing a contract that all implementing classes are able to complete the scheduled task, check
 * whether the task is completed and get a description of the task.
 */
public interface Schedulable {

    /**
     * Initiates a completion of the task.
     * State of completion will be changed to completed.
     */
    void completed();

    /**
     * Checks whether the task is completed is completed.
     * @return True if the task is completed.
     */
    boolean isCompleted();

    /**
     * Gets a description of the task.
     * @return String describing the task.
     */
    String showTaskDescription();

}
