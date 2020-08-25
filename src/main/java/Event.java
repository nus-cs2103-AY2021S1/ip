package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Encapsulates a task to be completed over a span of time (i.e period).
 */

public class Event extends Task {
    /**The date and time at which the event begins. */
    LocalDateTime start;
    /**The date and time at which the event ends. */
    LocalDateTime end;
    /**The format of inputted dates and times that the class can accept. */
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /**The format of outputted dates and times by the class. */
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * Constructs an event that has not been completed with a brief
     * description and period of time.
     *
     * @param description a brief description of the event.
     * @param period two dates and times with the format of inputFormatter in the form of
     *               -start- to -end-.
     */
    public Event (String description, String period) {
        super(description);
        this.start = LocalDateTime.parse(period.substring(0,15), inputFormatter);
        this.end = LocalDateTime.parse(period.substring(19), inputFormatter);
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param isDone indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param period two dates with the format of inputFormatter in the form of
     *               start date- to -end date-.
     */
    public Event(boolean isDone, String description, String period) {
        super(isDone, description);
        this.start = LocalDateTime.parse(period.substring(0,15), inputFormatter);
        this.end = LocalDateTime.parse(period.substring(19), inputFormatter);
    }

    /**
     * Returns the String representation of the period of time which the event occurred over. in the
     * form of -start- to -end-.
     * Dates and times are in the format of outputFormatter.
     *
     * @return the String representation of the period of which the event occurred over.
     */
    String getPeriod() {
        return this.start.format(outputFormatter).toString() + " to " + this.end.format(outputFormatter).toString();
    }

    /**
     * Returns the string representation of the event, which includes the status icon, description, and period.
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() +")";
    }

    /**
     * Returns a boolean value indicating if the event is equal to another object by
     * determining if isDone, start, end, and description parameters are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the event is equal or not equal to the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Event) {
            Event task = (Event) o;
            return this.description.equals(task.description) && this.start.equals(task.start) && this.end.equals(task.end) && this.isDone == task.isDone;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    @Override
    public String saveFormat() {
        if (isDone) {
            return "E | 1 | " + this.getDescription() + " | " + this.start.format(inputFormatter) + " to " + this.end.format(inputFormatter);
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.start.format(inputFormatter) + " to " + this.end.format(inputFormatter);
        }
    }
}

