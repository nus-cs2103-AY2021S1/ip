package duke.task;

/**
 * A type of Task that has description only
 */
public class ToDo extends Task {
    /**
     * Constructor for creating ToDo object
     *
     * @param description name of task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Alternative constructor that accounts for progress of task
     *
     * @param description name of task
     * @param isDone whether task is completed
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * toString method for ToDo
     *
     * @return task in string form
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
