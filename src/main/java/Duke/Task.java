package Duke;

/**
 * Represents Task
 */
public class Task {

    protected String description;
    protected boolean isDone;

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
        return (isDone ? "Y" : "X"); //return tick or cross symbols
    }

    /**
     * Mark Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Check Task isDone status.
     * @return  boolean value
     */
    public boolean checkDone() {
        return isDone;
    }

    /**
     * Get Task description
     * @return  String of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Convert Task object to a string representation.
     * @return  String value to be stored in file
     */
    public String toData () {
        return checkDone()
                ? "T//1//" + getDescription()
                : "T//0//" + getDescription();
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + getDescription();
    }

}
