package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns description of this task and its completion status.
     * @return String that describes task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
