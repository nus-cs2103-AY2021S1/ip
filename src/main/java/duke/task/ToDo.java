package duke.task;

/**
 * Encapsulates a ToDo task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

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
