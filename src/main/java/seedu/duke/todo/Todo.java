package main.java.seedu.duke.todo;

/**
 * Represent a todo item.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the date of the todo for storage.
     * @return the date of todo event.
     */
    @Override
    public String getData() {
        return "T " + super.getData();
    }
}
