package duke.task;

/**
 * Todo task that inherits from Task class
 */
public class ToDo extends Task {
    protected String by;

    public ToDo(String description) {
        super(description);
    }


    /**
     * Creates a new Task object and set its isDone boolean
     *
     * @param description details about the Task
     * @param isDone whether Task is done or not
     * @return Task with a corresponding description and completed status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if task contains the keyword
     *
     * @return whether the task contains that keyword
     */
    public boolean haveKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the todo task
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    public String infoString() {
        String x = "0";
        if (isDone) {
            x = "1";
        }
        return "T | " + x +  " | " + this.description;
    }
}
