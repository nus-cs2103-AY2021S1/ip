import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task which is a subclass of Task.
 */
public class Event extends Task {
    private final static String DATE_FORMAT = "MMM d yyyy";

    private LocalDate timeDescription;
    private String formattedTimeDescription;

    /**
     * Creates an Event object.
     * It is mainly for file writing.
     *
     * @param description is the description of the event.
     * @param timeDescription is the LocalDate input representing date.
     */
    public Event(String description, LocalDate timeDescription) {
        super(description, Task.EVENT_TASK);
        this.timeDescription = timeDescription;
        this.formattedTimeDescription = this.timeDescription.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Creates an Event object.
     * It is mainly for file reading.
     *
     * @param description is the description of the event.
     * @param formattedTimeDescription is the LocalDate input representing date.
     * @param isDone states if the Event object is completed or not.
     */
    public Event(String description, String formattedTimeDescription, boolean isDone) {
        super(description, Task.EVENT_TASK, isDone);
        this.formattedTimeDescription = formattedTimeDescription;
    }

    /**
     * Returns the String description of the completion time of Event object.
     *
     * @return Task description.
     */
    public String getFormattedTime() {
        return this.formattedTimeDescription;
    }

    /**
     * Returns a String representation of Event object.
     *
     * @return Event object description.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription()
                + "(at: " + getFormattedTime() + ")";
    }
}
