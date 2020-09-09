package duke;

/**
 * Represents Task
 */
public class Task {

    protected String description;
    protected boolean isDone;
    //protected DateAndTime dateTime;

    /**
     * Initialise a Task object
     * @param description  Description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get symbol of isDone status
     * @return  String showing either Y or X
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "X");
    }

    /**
     * Mark Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Check Task isDone status.
     * @return  boolean value
     */
    public boolean checkIsDone() {
        return isDone;
    }

    /**
     * Get Task description.
     * @return  String of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set Task description.
     * @param description  New description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Convert Task object to a string representation.
     * @return  String value to be stored in file
     */
    public String toData () {
        return checkIsDone()
                ? "T//1//" + getDescription()
                : "T//0//" + getDescription();
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + getDescription();
    }

}
