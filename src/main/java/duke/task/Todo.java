package duke.task;

/**
 * Represents a task without any date/time attached to it.
 */

public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    @Override
    public String toDataFileFormat() {
        return String.format("T | %d | %s | %s", isDone ? 1 : 0, tagName, content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
