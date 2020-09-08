package duke.tasks;

import duke.exception.DukeInvalidTaskException;

/**
 * The Todo class is used to represent the task of a Todo nature with no time specified
 * Inherits from Task class.
 */

public class Todo extends Task {

    /**
     * Initializes a Todo object
     *
     * @param taskName name or description of task
     * @throws DukeInvalidTaskException
     */
    public Todo(String taskName) throws DukeInvalidTaskException {
        super(taskName);
        assert taskName != null : "taskName should be null";
    }

    /**
     * Get a String representation of the object
     *
     * @return a String representing the object
     */

    @Override
    public String toString() {
        String finished = this.isDone ? "✓" : "✗";
        return "[T]" + "[" + finished + "] " + taskName;
    }
}
