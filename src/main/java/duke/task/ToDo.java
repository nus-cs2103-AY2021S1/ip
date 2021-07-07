package duke.task;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String sign = isDone ? "✓" : "✗";
        return "[T][" + sign + "] " + description;
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }
}
