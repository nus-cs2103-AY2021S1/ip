package duke.data.task;

/**
 * Basic task with only a description.
 */
public class ToDo extends Task {

    /**
     * Constructor for a basic To Do task containing a description.
     * @param description More information about the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
