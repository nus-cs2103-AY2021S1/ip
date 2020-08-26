package Task;

public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String saveFormat() {
        String base = "[T] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription;
        return base;
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
