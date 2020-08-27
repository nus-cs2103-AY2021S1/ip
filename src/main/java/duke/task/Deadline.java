package duke.task;

import duke.utils.Datetime;

import java.util.Optional;
import java.time.LocalDateTime;

/**
 * Represents Deadline objects.
 * Inherits from the abstract <code>Task</code> class.
 */
public class Deadline extends Task {
    private final Datetime datetime;

    public static final String DEADLINE_SYMBOL = "D";
    public static final String DATE_FORMAT_INPUT = "dd-MM-yyyy HHmm";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd yyyy hh:mm a";
    public static final String DEADLINE_BREAK = "/by";

    /**
     * Constructor method.
     * @param description the description of the <code>Deadline</code>.
     * @param isCompleted the completion status of the <code>Deadline</code>.
     * @param datetime the <code>LocalDateTime</code> the <code>Deadline</code> task is to be completed by.
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime datetime) {
        super(description, isCompleted);
        this.datetime = new Datetime(datetime, DATE_FORMAT_INPUT, DATE_FORMAT_OUTPUT);
    }

    /**
     * Creates a <code>Deadline</code> that is not completed.
     * @param description the description of the <code>Deadline</code>.
     * @param datetime the <code>LocalDateTime</code> the <code>Deadline</code> task is to be completed by.
     * @return an uncompleted <code>Deadline</code>.
     */
    public static Deadline createDeadline(String description , LocalDateTime datetime) {
        return new Deadline(description, false, datetime);
    }

    /**
     * Converts the <code>Deadline to a <code>String</code>.
     * @return a <code>String</code> representing the <code>Deadline</code>.
     */
    @Override
    public String toString() {
        String stringDateTime = this.datetime.getOutputDatetimeString();
        String byDatetime = String.format("(by: %s)", stringDateTime);
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }

    /**
     * Checks if the other object is equivalent to <code>Deadline</code>.
     * @param other the object to be compared to.
     * @return <code>true</code> if both objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            boolean isDatetimeEqual = this.getTaskDatetime().equals(otherDeadline.getTaskDatetime());
            return this.isEqual(otherDeadline) && isDatetimeEqual;
        }
        return false;
    }

    /**
     * Retrieves the symbol of <code>Deadline</code>.
     * @return the <code>String</code> symbol of <code>Deadline</code>.
     */
    @Override
    public String getTaskSymbol() {
        return DEADLINE_SYMBOL;
    }

    /**
     * Gets the datetime <code>String</code> of <code>Deadline</code>.
     * @return an <code>Optional</code> containing the formatted <code>String</code>.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        String stringDateTime = this.datetime.getOutputDatetimeString();
        return Optional.of(stringDateTime);
    }
}