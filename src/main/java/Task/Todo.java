package Task;

public class Todo extends Task {
    public Todo(int position, String taskDescription) {
        super(position, taskDescription);
    }

    @Override
    public String toString() {
        String base = "[T] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription;
        return base;
    }
}
