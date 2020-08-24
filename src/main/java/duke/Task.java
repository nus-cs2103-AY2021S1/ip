package main.java.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task represents a task by client.
 * Task is an abstract class for Todo, Deadline and Event to inherit.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String time;

    /**
     * Constructor of Task class.
     * 
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Second constructor of Task class.
     *
     * @param description description of the task.
     * @param time time estimated to finish the task.
     */
    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    /**
     * Gets status icon of the task.
     * 
     * @return String status icon of the task.
     */
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
     * Gets new format of the given time frame
     * 
     * @return String new format of time frame
     */
    public String getNewFormatTime() {
        try {
            LocalDate newFormatTime = LocalDate.parse(time);
            return newFormatTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return this.time;
        }
    }

    /**
     * Gets time as LocalDate format.
     * 
     * @return LocalDate new format of time.
     */
    public LocalDate getLocalDate() {
        return LocalDate.parse(time);
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Get type of the task.
     * 
     * @return String type of the task.
     */
    public abstract String getType();

    /**
     * Get task representation in hardware as string.
     */
    public abstract String toStringFile();
}