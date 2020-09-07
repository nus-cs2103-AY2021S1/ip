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

    public void edit(String editInput) {
        assert editInput.substring(0, 4) == "desc";
        super.description = editInput.substring(5);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
