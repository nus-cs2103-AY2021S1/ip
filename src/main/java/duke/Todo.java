package duke;

/**
 * Represents a toodo inherited from task.
 * A <code>Deadline</code> object corresponds to
 * a simple task without any constraints on dates
 */
public class Todo extends Task {
    public Todo(String message) {
        super(message);
    }

    Todo(String message, boolean isDone) {
        super(message, isDone);
    }

    @Override
    public String getPureTypeLetter() {
        return "T";
    }

    @Override
    public String getTypeLetter() {
        return "[T]";
    }

    @Override
    public String getPrintMessage() {
        return getMessage();
    }

    @Override
    public String getStoreMessage() {
        return getMessage();
    }
}
