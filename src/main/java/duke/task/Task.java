package duke.task;

import java.time.LocalDate;

import duke.exception.DukeException;

/**
 * An abstract class providing the blueprint for a task.
 */
public class Task {

    /** Boolean to determine if the task is completed. */
    protected boolean isDone;

    /** The description of the task. */
    private String name;

    /** Constructor to create a Task. */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Checks if the current task has a description that matches the search description.
     * @param description Represents the search keyword.
     * @return True if the description of the task matches the search keyword.
     *             Else returns false.
     * @throws DukeException
     */
    public boolean isResult(String description) throws DukeException {
        try {
            return this.name.contains(description);
        } catch (NullPointerException e) {
            throw new DukeException("    Please avoid using null as a search keyword.");
        }
    }

    /**
     * Mark the task as completed.
     */
    public void completed() {
        this.isDone = true;
    }

    /**
     * Converts the task to a writable format for the data file.
     * @return The string representation of the task for the data file.
     */
    public String toData() {
        return (isDone ? "1///" : "0///") + name + "///";
    }

    public boolean hasDate() {
        return false;
    }

    public boolean isOnDate(LocalDate date) {
        return false;
    }

    /**
     * Prints the string representation for the task for the user.
     * @return The string representation for the task for the user.
     */
    @Override
    public String toString() {
        return (isDone ? "[\u2705]" : "[\u2718]") + " " + name;
    }
}
