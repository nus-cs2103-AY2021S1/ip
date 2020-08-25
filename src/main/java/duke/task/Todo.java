package duke.task;

public class Todo extends Task {

    protected static final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s", Todo.TASK_TYPE, super.getSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.TASK_TYPE, super.toString());
    }
}
