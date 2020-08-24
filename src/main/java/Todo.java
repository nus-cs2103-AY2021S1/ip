public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String summarize() {
        return String.format("T | %s", super.summarize());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
