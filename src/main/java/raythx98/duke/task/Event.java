package raythx98.duke.task;

import raythx98.duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * Represents a specific task which is an event.
 */
public class Event extends Task {

    protected LocalDate date = null;
    protected String at = null;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        tag = "E";
        String[] bySplit = at.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.at = bySplit[1];
        }
    }

    public Event(String description) {
        super(description);
        tag = "E";
    }

    public Event(String ... taskDescriptions) throws DukeException {
        super(taskDescriptions[2]);
        tag = "E";
        if (taskDescriptions.length == 3) {
        } else if (taskDescriptions.length == 5) {
            this.date = LocalDate.parse(taskDescriptions[3]);
            this.at = taskDescriptions[4];
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

    public String toPrint() {
        if (date == null) {
            return super.toPrint();
        } else {
            return super.toPrint() + "|" + date + "|" + at;
        }
    }

    @Override
    public String toString() {
        if (at == null) {
            return "[E]" + super.toString();
        } else {
            String now = "AM";
            LocalTime localTime = LocalTime.parse(at, DateTimeFormatter.ofPattern("HHmm"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            if (hour > 12) {
                now = "PM";
                hour -= 12;
            }
            return "[E]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", " + hour + ":" + minute + now + ")";
        }
    }
}