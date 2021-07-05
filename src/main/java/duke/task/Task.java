package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates the parent class for all types of tasks.
 */
public class Task {

    private static final String DONE = "Y";
    private static final String NOT_DONE = "N";

    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * Creates a generic task with a description.
     * @param description The task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

        assert (this.description != null) : "The description cannot be left empty.";
    }

    public String getStatusIcon() {
        return isDone
                ? DONE
                : NOT_DONE;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Prints the date in a different format
     * @return The new format of the date.
     */
    public String printDate() {
        return this.date.getDayOfWeek() + ", " + this.date.getMonth() + " "
                + this.date.getDayOfMonth() + " " + this.date.getYear();
    }

    public void finishTask() {
        isDone = true;
    }

    public String saveData() {
        return getStatusIcon() + " > " + this.description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " > " + this.description;
    }


}
