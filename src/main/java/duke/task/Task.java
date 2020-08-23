package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates the parent class for all types of tasks.
 */
public class Task {
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
    }

    public String getStatusIcon() {
        return isDone
                ? "✓"
                : "✘";
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String printDate() {
        return this.date.getDayOfWeek() + ", " + this.date.getMonth() + " " +
                this.date.getDayOfMonth() + " " + this.date.getYear();
    }

    public void finishTask() { isDone = true; }

    public String saveData() {
        return getStatusIcon() + " > " + this.description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " > " + this.description;
    }


}
