package Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    protected Todo markAsDone() {
        return new Todo(super.description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
