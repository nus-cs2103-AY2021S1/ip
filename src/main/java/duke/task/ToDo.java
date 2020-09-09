package duke.task;

/**
 * Todo task that inherits from Task class.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new Task object and set its isDone boolean.
     *
     * @param description details about the Task.
     * @param isDone whether Task is done or not.
     * @return Task with a corresponding description and completed status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a new Task object and set its isDone boolean and its tag.
     *
     * @param description details about the Task.
     * @param isDone whether Task is done or not.
     * @param tagDescription description of tag for this task.
     * @return Task with a corresponding description and completed status.
     */
    public ToDo(String description, boolean isDone, String tagDescription) {
        super(description, isDone, tagDescription);
    }

    /**
     * Checks if task contains the keyword.
     *
     * @return whether the task contains that keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Overrides toString method of Task class.
     *
     * @return Custom description of the todo task.
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
        String infoIsDone = "0";
        if (isDone) {
            infoIsDone = "1";
        }
        return "T | " + infoIsDone + " | " + this.description + " | " + this.tagDescription;
    }
}
