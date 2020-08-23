public class Todo extends Task {
    private static final String STRING_FORMAT = "[T][%s] %s";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s";

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toTaskData() {
        return String.format(Todo.TASK_DATA_FORMAT, "T", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format(Todo.STRING_FORMAT, getStatusIcon(), description);
    }
}
