import java.util.Date;

/**
 * Task is part of a TaskList.
 */

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected final TaskType taskType;

    /**
     * Creates a new Task.
     * Constructor used by subclasses since Task is an abstract class.
     *
     * @param description Description of task.
     * @param taskType    Type of Task.
     * @param isDone      true if Task is done, false if Task is yet to be done.
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
    }

    public void markAsDone() throws TaskException {
        if (isDone) {
            throw new TaskException(taskType, "done status", TaskExceptionType.DONE);
        } else {
            isDone = true;
        }
    }

    /**
     * Used by Storage to record the details of a Task.
     *
     * @return String that contains the details of a Task and is saved in a specified file.
     */
    public String getSavedString() {
        return taskType.getSymbol() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public boolean isOccuringOn(Date date) {
        return false;
    }

    /**
     * Checks if Task fulfil date and keyword criteria.
     *
     * @param date Date that Task must be on but if date is null, Task is deemed to have fulfiled criteria.
     * @param keyWord Keyword that Task description must contain but if keyWord is null, Task is deemed to have
     *                fulfiled criteria.
     * @return boolean indicates whether Task passed the criteria.
     */
    public boolean fulfilCriteria(Date date, String keyWord) {
        return (date == null || isOccuringOn(date)) && (keyWord == null
                || description.toLowerCase().contains(keyWord.toLowerCase()));
    }

    @Override
    public String toString() {
        return "[" + taskType.getSymbol() + "]" + getStatusIcon() + " " + description;
    }
}
