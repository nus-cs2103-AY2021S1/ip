package duke.task;

/**
 * A sub class of Task, which represents any todo tasks.
 */
public class ToDo extends Task {

    /**
     * ToDo constructor.
     * @param description Details of the task.
     * @param isDone Progress of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.toString();
    }
}
