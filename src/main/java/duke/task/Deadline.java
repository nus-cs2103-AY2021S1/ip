package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.utils.Datetime;

/**
 * Represents Deadline objects.
 * Inherits from the abstract <code>Task</code> class.
 */
public class Deadline extends Task {
    public static final String DEADLINE_SYMBOL = "D";
    public static final String DATE_FORMAT_INPUT = "dd-MM-yyyy HHmm";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd yyyy HH:mm";
    public static final String DEADLINE_BREAK = "/by";

    private final Datetime datetime;

    /**
     * Constructor method.
     *
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
     *
     * @param description the description of the <code>Deadline</code>.
     * @param datetime the <code>LocalDateTime</code> the <code>Deadline</code> task is to be completed by.
     * @return an uncompleted <code>Deadline</code>.
     */
    public static Deadline createDeadline(String description , LocalDateTime datetime) {
        return new Deadline(description, false, datetime);
    }

    /**
     * Converts the <code>Deadline</code> to a <code>String</code>.
     *
     * @return a <code>String</code> representing the <code>Deadline</code>.
     */
    @Override
    public String toString() {
        String stringDateTime = datetime.getOutputDatetimeString();
        String byDatetime = String.format("(by: %s)", stringDateTime);
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }

    /**
     * Checks if the other object is equivalent to <code>Deadline</code>.
     * The description, completion status and datetime must be equal.
     *
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
     *
     * @return the <code>String</code> symbol of <code>Deadline</code>.
     */
    @Override
    public String getTaskSymbol() {
        return DEADLINE_SYMBOL;
    }

    /**
     * Gets the datetime <code>String</code> of <code>Deadline</code>.
     *
     * @return an <code>Optional</code> containing the formatted <code>String</code>.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        String stringDateTime = datetime.getOutputDatetimeString();
        return Optional.of(stringDateTime);
    }
}
