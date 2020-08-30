package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * Represents a specific task which has a deadline.
 */
public class Deadline extends Task {

    protected LocalDate date = null;
    protected String by = null;

    public Deadline(String description, String by) {
        super(description);
        tag = "D";
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.by = bySplit[1];
        }
    }

    public Deadline(String description) {
        super(description);
        tag = "D";
    }

    public Deadline(String ... taskDescriptions) throws DukeException {
        super(taskDescriptions[2]);
        tag = "D";
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

    public String toPrint(){
        return date == null
                ? super.toPrint()
                : super.toPrint() + "|" + date + "|" + by;
    }

    @Override
    public String toString() {
        if (by == null) {
            return "[D]" + super.toString();
        } else {
            String now = "AM";
            LocalTime localTime = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            if (hour > 12) {
                now = "PM";
                hour -= 12;
            }
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", " + hour + ":" + minute + now + ")";
        }
    }
}