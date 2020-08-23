public class Todo extends Task {
    private static final String STRING_FORMAT = "[T][%s] %s";

    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public boolean hasDateTime() {
        return false;
    }

    @Override
    public String toString() {
        return String.format(Todo.STRING_FORMAT, getStatusIcon(), description);
    }
}
