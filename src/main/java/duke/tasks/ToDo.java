package duke.tasks;

public class ToDo extends Task {
    /**
     * ToDo constructor
     *
     * @param description Description string
     * @param isDone Completion status of todo
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
