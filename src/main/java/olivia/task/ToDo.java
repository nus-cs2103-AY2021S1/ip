package olivia.task;

/**
 * ToDo class that represents a Task that has to be done. Extends from the Task class.
 */

public class ToDo extends Task {

    /**
     * Constructor that creates a ToDo object that has a description of the
     * task and whether the task has been completed.
     *
     * @param description a String representing the description of the task
     * @param isDone a boolean representing whether the task has been completed
     */

    public ToDo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

}
