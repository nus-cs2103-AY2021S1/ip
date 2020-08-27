package alice.task;

import alice.AliceException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter D_DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu, ha");
    private static final DateTimeFormatter D_DATE_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu");

    private final LocalDateTime by;
    private final boolean includesTime;

    /**
     * Creates an undone task with the specified deadline.
     *
     * @param description describes the task to be done before the deadline.
     * @param by the latest datetime by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.includesTime = !by.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    /**
     * Creates a task with the specified deadline and completion status.
     *
     * @param isDone the completion status of the task, true if completed; false otherwise.
     * @param description describes the task to be done before the deadline.
     * @param by the latest datetime by which the task should be completed.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
        this.includesTime = !by.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    /**
     * Gets the string representation of the deadline by which the task should be completed by.
     * If time is not specified by user, the time is omitted from the string representation.
     *
     * @return the appropriate string representation of the deadline datetime
     */
    public String getDeadlineDateTime() {
        if (includesTime) {
            return by.format(D_DATETIME_FORMAT);
        } else {
            return by.format(D_DATE_FORMAT);
        }
    }

    /**
     * Decode an encoded string representation of the <code>Deadline</code>.
     *
     * @param saved the string representation of the encoded task with deadline.
     * @return the <code>Deadline</code> described in the string representation.
     * @throws AliceException if the encoded string is corrupted.
     */
    public static Deadline decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Deadline(isDone, inputs[1], LocalDateTime.parse(inputs[2]));
        } else {
            throw new AliceException("Corrupted deadline data");
        }
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineDateTime() + ")";
    }
}
