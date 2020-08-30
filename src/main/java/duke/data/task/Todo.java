package src.main.java.duke.data.task;

/**
 * The Todo class that represents a Todo task.
 *
 */
public class Todo extends Task {

    public Todo(String description) {
        this.description = description;
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This method returns the string to be written for the todo.
     */
    public String toWriteString() {
        return "T " + super.toWriteString();
    }
}
