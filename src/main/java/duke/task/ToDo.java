package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String formatStyling() {
        return String.format("todo%s", super.formatStyling());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
