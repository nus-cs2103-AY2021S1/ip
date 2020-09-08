package duke.task;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone, String priority) {
        super(description, isDone, priority);
    }

    @Override
    public String getStorageString() {
        return "T | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.priority.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description
                + " (Priority: " + this.priority.toString() + ")";
    }
}
