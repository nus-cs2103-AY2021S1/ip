import java.time.LocalDate;

/**
 * Encapsulates a Task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract LocalDate getDate();

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Saves whether the task is done or not into storage.
     * @return a string representation of the task.
     */
    public String saveTask() {
        String doneOrNot = isDone ? "1" : "0";
        return " | " + doneOrNot + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
