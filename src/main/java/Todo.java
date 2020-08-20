public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String saveText() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
