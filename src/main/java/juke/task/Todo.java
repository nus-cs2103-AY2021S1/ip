package juke.task;

/**
 * Represents a Todo class with an input description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the input description
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the representative text of the Todo.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "T";
        return type + "/" + super.taskSaver();
    }

    /**
     * Outputs the Todo as a String.
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
