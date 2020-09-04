package raythx98.grandma.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import raythx98.grandma.exception.DukeException;

/**
 * Represents a specific task which is a to do.
 */
public class ToDos extends Task {

    protected LocalDate date = null;
    protected String by = null;

    /**
     * Something.
     *
     * @param description
     * @param by
     */
    public ToDos(String description, String by) {
        super(description);
        tag = "T";
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.by = bySplit[1];
        }
    }

    /**
     * Something.
     *
     * @param description
     */
    public ToDos(String description) {
        super(description);
        tag = "T";
    }

    /**
     * Something.
     *
     * @param taskDescriptions
     * @throws DukeException
     */
    public ToDos(String ... taskDescriptions) throws DukeException {
        super(taskDescriptions[2]);
        tag = "T";
        if (taskDescriptions.length == 3) {
        } else if (taskDescriptions.length == 5) {
            this.date = LocalDate.parse(taskDescriptions[3]);
            this.by = taskDescriptions[4];
        } else {
            throw new DukeException("Task loading error...");
        }
        if (taskDescriptions[1].equals("1")) {
            this.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    /**
     * Something
     *
     * @return
     */
    public String toPrint() {
        if (date == null) {
            return super.toPrint();
        } else {
            return super.toPrint() + "|" + date + "|" + by;
        }
    }

    @Override
    public String toString() {
        if (by == null) {
            return "[T]" + super.toString();
        } else {
            String now = "AM";
            LocalTime localTime = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            if (hour > 12) {
                now = "PM";
                hour -= 12;
            }
            return "[T]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", " + hour + ":" + minute + now + ")";
        }
    }
}
