package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description;
    }
}
