package duke.task;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO, "-");
    }

    public ToDo(String description) {
        super(description, false, TaskType.TODO, "-");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
