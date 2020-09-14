package duke.task;

/**
 * A sub class of Task, which represents any todo tasks.
 */
public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param description Details of the task.
     * @param isDone      Progress of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.toString();
    }
}
