package duke.task;

import duke.exception.WrongFormatException;

/**
 * Encapsulates the to-do task type. A to-do task only has a description of the task.
 */
public class ToDo extends Task {

    /**
     * Creates and initializes a to-do task that has not been completed by default.
     *
     * @param description The description of the task.
     * @throws WrongFormatException If no description is provided.
     */
    public ToDo(String description) throws WrongFormatException {
        super(description, "[T]", "todo", false);
    }

    /**
     * Creates and initializes a to-do task that can be marked as completed.
     *
     * @param description The description of the task.
     * @param isDone Marks whether the task has been completed or not.
     * @throws WrongFormatException If no description is provided.
     */
    public ToDo(String description, boolean isDone) throws WrongFormatException {
        super(description, "[T]", "todo", isDone);
    }
}