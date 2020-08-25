package main.java;

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
    public Todo(String taskName) throws DukeInvalidTaskException{
        super(taskName);
    }

    /**
     * Get a String representation of the object
     *
     * @return a String representing the object
     */

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        return "[T]" + "[" + finished + "] " + taskName;
    }
}
