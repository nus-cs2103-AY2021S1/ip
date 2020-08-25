public class Todo extends Task {
    private String type = "[T]";

    public Todo(String isCompleted, String taskName) {
        super(isCompleted, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
