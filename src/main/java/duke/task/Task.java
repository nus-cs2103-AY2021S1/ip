package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Task is a class for each task specified from the user commands */
public abstract class Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
    protected final String name;
    protected final boolean isDone;
    protected LocalDate schedule;

    /**
     * Initializes a task by checking its name and whether it is done.
     *
     * @param name The name of the task.
     * @param isDone The boolean indicating if the task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.schedule = null;
    }

    /**
     * Gets the name of the task.
     *
     * @return A String showing the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the status of the task.
     *
     * @return A boolean indicating if the task is done.
     */
    public boolean isDoneTask() {
        return this.isDone;
    }

    /**
     * Marks the status of the task as done.
     *
     * @return A new Task object with the status updated to "done".
     */
    public abstract Task complete();

    /**
     * Formats the task for data output display in the duke.txt file.
     *
     * @return A string to represent the task.
     */
    public abstract String formatTask();

    /**
     * Check if a task is stored with some schedule information.
     *
     * @return True only if it is a deadline or an event.
     */
    public boolean isScheduled() {
        return this.schedule != null;
    }

    /**
     * Return the schedule of the task (if exists) in LocalDate format.
     *
     * @return The LocalDate representation of the task's schedule.
     */
    public LocalDate getScheduleInLocalDate() {
        assert this.schedule != null : "The task without a schedule should not use getScheduleInLocalDate method";
        return this.schedule;
    }

    /**
     * Convert the schedule events to a string with a check on whether the scheduled date has past.
     *
     * @return a string with the task's name and schedule, and an indication of whether the task expires.
     */
    public String toStringWithExpiryCheck() {
        assert this.schedule != null : "The task without a schedule should not use toStringWithExpiryCheck method";

        int hasExpiredSignal = this.schedule.compareTo(LocalDate.now());
        String expirySign = hasExpiredSignal < 0 ? "[Schedule past!]" : "";

        return this.toString() + expirySign;
    }

    /**
     * Returns a string representation of the task's schedule in the pattern "MMM d yyyy".
     *
     * @return The string representation of the schedule.
     */
    public String getSchedule() {
        assert this.schedule != null : "The task without a schedule should not use getSchedule method";
        return this.schedule.format(Task.FORMATTER);
    }

    /**
     * Displays the task object as a string
     *
     * @return The status of the task followed by the name
     */
    @Override
    public String toString() {
        String checkbox = "[" + (this.isDoneTask() ? "\u2713" : "\u2718") + "]";
        return checkbox + " " + this.getName();
    }
}
