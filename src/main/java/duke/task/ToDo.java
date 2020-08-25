package duke.task;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getStorageFormat() {
        return "T | " + super.getStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
