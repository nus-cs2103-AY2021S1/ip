import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which is a subclass of Task.
 */
public class Deadline extends Task {
    private final static String DATE_FORMAT = "MMM d yyyy";

    private LocalDate timeDescription;
    private String formattedTimeDescription;

    /**
     * Creates a Deadline object.
     * It is mainly for file writing.
     *
     * @param description is the description of the deadline.
     * @param timeDescription is the LocalDate input representing date.
     */
    public Deadline(String description, LocalDate timeDescription) {
        super(description, Task.DEADLINE_TASK);
        this.timeDescription = timeDescription;
        this.formattedTimeDescription = this.timeDescription.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Creates a Deadline object.
     * It is mainly for file reading.
     *
     * @param description is the description of the deadline.
     * @param formattedTimeDescription is the LocalDate input representing date.
     * @param isDone states if the Deadline object is completed or not.
     */
    public Deadline(String description, String formattedTimeDescription, boolean isDone) {
        super(description, Task.DEADLINE_TASK, isDone);
        this.formattedTimeDescription = formattedTimeDescription;
    }

    /**
     * Returns the String description of the completion time of Deadline object.
     *
     * @return Task description.
     */
    public String getFormattedTime() {
        return this.formattedTimeDescription;
    }

    /**
     * Returns a String representation of Deadline object.
     *
     * @return Deadline object description.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription()
                + "(by: " + getFormattedTime() + ")";
    }
}
