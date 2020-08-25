package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Encapsulates a task
 * All types of task must extend from this class
 */
public abstract class Task {

    /** Date of the task, null if date is not associated with the task */
    final LocalDate date;

    /** Description of the task */
    final String description;

    /** Completion status of the task */
    boolean isComplete;

    /**
     * Constructor
     *
     * @param description Description of the task
     * @param isCompleted Completion status of the task
     * @param date Date of the task, null if date is not associated with the task
     */
    Task(String description, boolean isCompleted, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isCompleted;
    }

    /**
     * Marks the task as complete
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Gets the string representation of the task to be written into the file upon exit
     *
     * @return String representation of the task in the file
     */
    public String[] getDataString() {
        return new String[] {"task", String.valueOf(isComplete), description};
    }

    /**
     * Gets the string representation of the date of the task to be printed in the UI
     *
     * @return String representation of the date of the task in the UI
     */
    String getDateString() {
        if (this.date == null) {
            return null;
        } else {
            return this.date.getYear() + " " +
                    this.date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                    this.date.getDayOfMonth();
        }
    }

    /**
     * Gets the status icon reflecting the completion status of the task
     *
     * @return Status icon
     */
    String getStatusIcon() {
        if (this.isComplete) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Gets the string representation of the task to be printed in the UI
     *
     * @return String representation of the task in the UI
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
