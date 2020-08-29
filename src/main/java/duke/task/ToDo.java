package duke.task;

/**
 * Encapsulates a ToDo task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo event.
     * @param description The task details.
     * @param isDone Informs program if task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String saveData() {
        return "T > " + super.saveData();
    }

    @Override
    public String toString() {
        return "T > " + super.toString();
    }

}
