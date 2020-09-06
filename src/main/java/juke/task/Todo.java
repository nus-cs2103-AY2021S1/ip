package juke.task;

/**
 * A juke.task.Deadline class that represents a juke.task.Todo.
 * It has a juke.task.Task description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the representative text of the juke.task.Todo.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "T";
        return type + "/" + super.taskSaver();
    }

    /**
     * Outputs the juke.task.Todo as a String.
     * @return String representation of juke.task.Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
