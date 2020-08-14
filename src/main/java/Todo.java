public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
