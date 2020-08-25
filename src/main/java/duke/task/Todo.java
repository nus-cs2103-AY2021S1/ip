package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String toStorageString() {
        if (super.isDone) return "T | 1 | " + description;
        else return "T | 0 | " + description;
    }
}