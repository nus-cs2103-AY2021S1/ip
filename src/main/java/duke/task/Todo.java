package duke.task;

public class Todo extends Task {
    public static final String TASK_ICON = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.TASK_ICON + "]" + super.toString();
    }
}
