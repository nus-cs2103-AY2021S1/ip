public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveAsString() {
        return "T" + super.saveAsString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}