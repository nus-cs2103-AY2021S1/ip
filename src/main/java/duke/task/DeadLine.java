package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadLine extends Task {
    private LocalDate deadLine;
    private LocalDateTime deadLineWithTime;
    private boolean hasTime;

    /**
     * Initializes a deadline task.
     *
     * @param description Description of the deadline.
     * @param time        Date/Time of the deadline.
     * @param hasTime     Whether there is a specified time for the deadline.
     * @param isDone      Whether the deadline is finished or not.
     * @param priority    Priority level of the deadline.
     */
    public DeadLine(
            String description, String time, boolean hasTime, boolean isDone, String priority) {
        super(description, isDone, priority);
        this.hasTime = hasTime;
        if (!hasTime) {
            this.deadLine = LocalDate.parse(time);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.deadLineWithTime = LocalDateTime.parse(time, format);
        }
    }

    /**
     * Gets the string representation of the event task to be stored in the data file.
     *
     * @return String representation of the event task in the data file.
     */
    @Override
    public String getStorageString() {
        return "D | " + this.getStatusIcon() + " | " + this.description
                + " | " + (!hasTime ? this.deadLine : this.deadLineWithTime)
                + " | " + this.priority.toString() + "\n";
    }

    @Override
    public String toString() {
        String datePrintFormat;
        if (deadLine != null) {
            datePrintFormat = deadLine.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            datePrintFormat = deadLineWithTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[D][" + this.getStatusIcon() + "] "
                + this.description + " (by: " + datePrintFormat + ")"
                + " (Priority: " + this.priority.toString() + ")";
    }
}
