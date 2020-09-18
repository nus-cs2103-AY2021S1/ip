package duke.task;

import java.time.LocalDate;

// Task Layout adapted from @nicholas-gcc's ip code
public class Task {
    protected String description;
    protected String identity;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Function to mark Task instance as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Function to identify the different tasks in duke.txt
     * @return String of identity
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Function to get special status icon symbol done or not done
     * @return String tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter to return description of Task
     * @return String of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter to return whether Task is done
     * @return boolean isDone
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getter to return date of Task
     * @return LocalDate
     */
    public LocalDate getLocalDate() {
        assert !(this instanceof Todo) : "Todos have no dates!";
        return date;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}