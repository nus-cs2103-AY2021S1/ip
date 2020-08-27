package duke.task;

import java.util.Optional;

/**
 * This class is a representation of a Task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description Description of the given Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status of a Task: done or not done.
     * Returns a symbol: a tick for a completed Task, cross for an incomplete Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the status of a Task from !isDone to isDone.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the Task isDone.
     */
    public boolean isDone() { return isDone; }

    /**
     * Returns 1 if Task isDone, 0 otherwise.
     */
    public String isDoneToString() { return isDone ? "1" : "0"; }

    /**
     * Method for returning the letter representation of a Task.
     * 'E' for Event, 'D' for Deadline etc.
     */
    public abstract String getStringType();

    /**
     * Returns a stored date/time in the form of an Optional.
     * Todo does not have any date associated, so this returns an empty Optional.
     * Deadline and Event tasks will have an associated date/time.
     */
    public abstract Optional<String> getDate();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public boolean contains(String keyWords) {
        return description.contains(keyWords);
    }
}
