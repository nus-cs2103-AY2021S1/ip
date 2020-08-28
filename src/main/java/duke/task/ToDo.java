package duke.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
    
    public ToDo(String task, boolean done) {
        super(task, done);
    }

    /**
     * Returns a String representation of the task to be stored in the 
     * storage file.
     *
     * @return Formatted String representing the task.
     */
    @Override
    public String toDataString() {
        return "T // " + (done ? "1" : "0") + " // " + task;
    }

    /**
     * Returns a String representation of the todo for display.
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
