package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    String getTaskDetailsForSave() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
