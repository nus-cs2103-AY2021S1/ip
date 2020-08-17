public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }

    private Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    @Override
    public Todo markDone() {
        return new Todo(this.description, true);
    }
}
