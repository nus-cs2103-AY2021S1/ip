package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    protected LocalDateTime by;

    /**
     * Creates an Deadline with the specified values for description and by, which is marked incomplete by default.
     * Used when creating a new Deadline in the programme.
     * @param description Description of the deadline.
     * @param by The time which the deadline should be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, dateTimeInputFormatter);
    }

    /**
     * Creates an Deadline with the specified values for description, isDone and by.
     * Used when loading tasks from file.
     * @param description Description of the deadline.
     * @param isDone Completion status of the deadline.
     * @param by The time which the deadline should be completed by.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by); // use default formatter that leaves no whitespace to trim
    }

    /**
     * Returns an array of Strings representing the state of the task, to be passed to Storage to
     * be formatted and written to a file.
     * @return Array of Strings representing the current state of the Task.
     */
    @Override
    public String[] serialize() {
        String[] output = new String[4];
        output[0] = this.isDone
                ? "1"
                : "0";
        output[1] = "Deadline";
        output[2] = description;
        output[3] = by.toString();

        return output;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(dateTimeOutputFormatter) + ")";
    }
}
