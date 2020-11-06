package tasks;

/**
 * Represents a Todo task with a name.
 */

public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeString() {
        return "T # " + super.writeString();
    }
}
