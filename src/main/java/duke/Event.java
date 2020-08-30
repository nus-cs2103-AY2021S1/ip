package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from the Task class. Represents a event happening at a certain time.
 * Event object stores task description, done status, date of event, start and end time of event.
 * A Event object is marked as not done by default.
 * A Event object is able to be set as done by calling setDone() method from the parent class.
 */
public class Event extends Task {
    protected LocalDate at;
    protected LocalTime start;
    protected LocalTime end;

    /**
     * Event object constructor overrides Task constructor.
     * Takes in date of event, start and end time of event in addition to the task description
     * and parses into LocalDate and LocalTime format.
     * @param description description of task
     * @param at date of event
     * @param timeRange start and end time of event
     */
    public Event (String description, String at, String timeRange) {
        super(description);
        this.at = LocalDate.parse(at);
        this.start = LocalTime.parse(timeRange.split("-")[0]);
        this.end = LocalTime.parse(timeRange.split("-")[1]);
    }

    /**
     * Returns Event object as a string.
     * This method takes no parameters and returns the Event object as a string
     * in the form "[E][<Done Status>] <Event Description> (at: <Date> <Start>-<End>)".
     * This method overrides the method from parent class.
     * @return String this returns the event object as a string
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString() + " (at: "
                + dateFormatter.format(at) + " "
                + timeFormatter.format(start) + "-"
                + timeFormatter.format(end) + ")";
    }

    /**
     * Returns Event object as a string.
     * This method takes no parameters and returns the Event object as a string
     * as a string format suitable for being parsed into a Deadline object.
     * String is in the form "[E][<Done Status>] <Event Description> (at: <Date> <Start>-<End>)".
     * This method overrides the method from parent class.
     * @return String this returns the Event object as a string
     */
    @Override
    public String toStringFileFormat() {
        return "[E]" + super.toStringFileFormat() + " (at: "
                + at + " " + start + "-" + end + ")";
    }
}
