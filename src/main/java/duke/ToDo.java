package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + "[" + getStatusIcon() + "]" + description;
    }
}
