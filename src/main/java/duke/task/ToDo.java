package main.java.duke.task;

/**
 * Represents a task with a todo.
 */
public class ToDo extends Task {

    /**
     * Creates a new instance of a Todo.
     *
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts a ToDo object to a string.
     *
     * @return A string displaying the task and its status.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}


