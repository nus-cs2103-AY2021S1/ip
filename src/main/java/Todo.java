public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    String[] parts = description.split("todo ");

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]  " + parts[1];
    }
}