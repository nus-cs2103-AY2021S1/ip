public class Todo extends Task {
    private static final String identifier = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.identifier, super.toString());
    }

    @Override
    public String serialise() {
        return String.format("%s | %s", Todo.identifier, super.serialise());
    }
}
