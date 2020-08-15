package task;

/**
 * Represents a to-do task
 */
public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public Todo complete() {
        return new Todo(this.getName(), true);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[T]" + taskString;
        return result;
    }
}