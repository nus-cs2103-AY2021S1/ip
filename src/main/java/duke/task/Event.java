package main.java.duke.task;

import java.time.LocalDate;

/**
 * Encapsulates an event
 */
public class Event extends Task {

    /**
     * Constructor
     *
     * @param description Description of the event
     * @param isComplete Completion status of the event
     * @param date Date of the event
     */
    public Event(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    /**
     * Gets the string representation of the event to be written into the file upon exit
     *
     * @return String representation of the event in the file
     */
    @Override
    public String[] getDataString() {
        return new String[] {"deadline", String.valueOf(this.isComplete), this.description, this.date.toString()};
    }

    /**
     * Gets the string representation of the event to be printed in the UI
     *
     * @return String representation of the event in the UI
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + super.getDateString() + ")";
    }
}
