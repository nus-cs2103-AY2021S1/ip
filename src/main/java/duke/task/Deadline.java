package duke.task;

import duke.utils.Datetime;

import java.util.Optional;
import java.time.LocalDateTime;

/**
 * Represents Deadline objects.
 * Inherits from the abstract Task class.
 */
public class Deadline extends Task {
    private final Datetime datetime;

    public static final String DEADLINE_SYMBOL = "D";
    public static final String DATE_FORMAT_INPUT = "dd-MM-yyyy HHmm";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd yyyy hh:mm a";
    public static final String DEADLINE_BREAK = "/by";
    public static final int COMMAND_LENGTH = 2;

    /**
     * Constructor method.
     * @param description the description of the Deadline.
     * @param isCompleted the completion status of the Deadline.
     * @param datetime the LocalDateTime the Deadline task is to be completed by.
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime datetime) {
        super(description, isCompleted);
        this.datetime = new Datetime(datetime, DATE_FORMAT_INPUT, DATE_FORMAT_OUTPUT);
    }

    /**
     * Creates a Deadline object that is not completed.
     * @param description the description of the Deadline.
     * @param datetime the LocalDateTime the Deadline task is to be completed by.
     * @return an uncompleted Deadline object.
     */
    public static Deadline createDeadline(String description , LocalDateTime datetime) {
        return new Deadline(description, false, datetime);
    }

    /**
     * Converts the Deadline object to a String,
     * @return a String representing the Deadline object.
     */
    @Override
    public String toString() {
        String stringDateTime = this.datetime.getOutputDatetimeString();
        String byDatetime = String.format("(by: %s)", stringDateTime);
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }

    /**
     * Checks if the other Object is equivalent to the Deadline object.
     * @param other the object to be compared to.
     * @return true if both objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.isEqual(otherDeadline);
        }
        return false;
    }

    /**
     * Retrieves the symbol of Deadline.
     * @return the String symbol of Deadline.
     */
    @Override
    public String getTaskSymbol() {
        return DEADLINE_SYMBOL;
    }

    /**
     * Gets the Datetime String of Deadline.
     * @return an Optional object containing the formatted String.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        String stringDateTime = this.datetime.getOutputDatetimeString();
        return Optional.of(stringDateTime);
    }
}