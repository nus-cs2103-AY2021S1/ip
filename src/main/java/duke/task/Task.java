package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task class that is used to store the different tasks that the user decides to store in the tasklist.
 */
public class Task {
    protected boolean isDone;
    protected final String name;
    protected LocalDateTime createdDateTime;
    protected DateTimeFormatter formatter;

    /**
     * Constructor for a new task object.
     * @param name Name of the task
     * @param time Time when the task is created
     */
    protected Task(String name, LocalDateTime time) {
        this.isDone = false;
        this.name = name;
        this.createdDateTime = time;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * Overloaded constructor for the task object. This is mainly used when the Storage class would have to
     * update the tasklist based on the txt file.
     * @param line input from the text file
     */
    protected Task(String line) {
        this.isDone = line.charAt(4) == '\u2713';
        this.name = line.substring(7, line.indexOf("[created on"));
        this.createdDateTime = LocalDateTime.parse(line.substring(line.indexOf("[created on ")+ 12,
                line.lastIndexOf("]")),
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * Getter for the name of the task
     * @return name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the date and time .
     * @return the date and time in MMM d yyyy HH:MM format
     */
    public String getDateTime() {
        return createdDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Setter to set the boolean flag from an uncomplete task(false) to a complete one(true)
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Get the status icon to indicate whether a task is incomplete or complete
     * @return tick or cross icon
     */
    protected String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    @Override
    /**
     * String representation of the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.getName() + " [created on " + this.getDateTime()
                + "] ";
    }
}
