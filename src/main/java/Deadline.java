import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which is a subclass of Task.
 */
public class Deadline extends Task {
    private LocalDate timeDescription;
    private String timeDescriptionFormatted;

    /**
     * Creates a Deadline object.
     * It is mainly for file writing.
     *
     * @param description is the description of the deadline.
     * @param timeDescription is the LocalDate input representing date.
     */
    public Deadline(String description, LocalDate timeDescription) {
        super(description, "D");
        this.timeDescription = timeDescription;
        this.timeDescriptionFormatted = this.timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Creates a Deadline object.
     * It is mainly for file reading.
     *
     * @param description is the description of the deadline.
     * @param timeDescriptionFormatted is the LocalDate input representing date.
     * @param isDone states if the Deadline object is completed or not.
     */
    public Deadline(String description, String timeDescriptionFormatted, boolean isDone) {
        super(description, "D", isDone);
        this.timeDescriptionFormatted = timeDescriptionFormatted;
    }

    /**
     * Returns a String representation of Deadline object.
     *
     * @return Deadline object description.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description
                + "(by: " + this.timeDescriptionFormatted + ")";
    }
}
