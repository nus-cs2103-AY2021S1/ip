package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String taskToText() {
        return "T|" + super.completed + "|" + super.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
