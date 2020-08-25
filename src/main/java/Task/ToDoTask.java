package Task;

public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
