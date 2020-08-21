public class Todo extends Task {
    private static final String STRING_FORMAT = "[T][%s] %s";

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format(Todo.STRING_FORMAT, getStatusIcon(), description);
    }
}
