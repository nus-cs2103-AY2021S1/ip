public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String taskRow() {
        return "[T]" + " " + getStatusIcon() + " " + this;
    }
}
