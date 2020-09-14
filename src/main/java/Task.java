import java.time.LocalDate;

/**
 * Class to represent a Task object.
 * @author vanGoghhh
 */

public class Task {

    protected String description;
    protected boolean isDone;
    protected boolean isBeingUpdated = false;

    /**
     * Constructor for Task.
     * @param description description of the task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return icon to indicate whether a task is done.
     * @return a "check" or a "cross" depending on whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Get the status of a task.
     * @return boolean
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Get task status in the form of an integer.
     * @return 1 or 0 depending on status on task.
     */
    private int getTaskStatus() {
        return this.isDone ? 1 : 0;
    }

    protected void toBeUpdated() {
        isBeingUpdated = true;
    }

    /**
     * Prints the task object.
     * @return string representation of a task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    protected LocalDate getTaskDeadline() {
        return null;
    }

    /**
     * Transform the task into a file friendly format.
     * @return string representation of task in file format.
     */
    protected String inputInFile() {
        if (this instanceof Todo) {
            return "T//" + this.getTaskStatus() + "//"
                    + this.description;
        }
        if (this instanceof Event) {
            return "E//" + this.getTaskStatus() + "//"
                    + this.description + "//" + ((Event) this).getTaskDeadline();
        }
        if (this instanceof Deadline) {
            return "D//" + this.getTaskStatus() + "//"
                    + this.description + "//" + ((Deadline) this).getTaskDeadline();
        }
        return " ";
    }

}
