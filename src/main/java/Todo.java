public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
