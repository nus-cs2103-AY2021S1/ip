package src.main.java.duke.data.task;

/**
 * Represents a todo task.
 *
 */
public class Todo extends Task {

    public Todo(String description) {
        this.description = description;
    }

    /**
     * Gets the string to be printed for the todo.
     * @return string to be printed for todo
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the string to be written for the todo.
     * @return string to be written for todo
     */
    public String toWriteString() {
        return "T " + super.toWriteString();
    }
}
