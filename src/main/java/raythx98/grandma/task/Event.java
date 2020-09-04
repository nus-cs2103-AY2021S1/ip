package raythx98.grandma.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import raythx98.grandma.exception.DukeException;

/**
 * Represents a specific task which is an event.
 */
public class Event extends Task {

    protected LocalDate date = null;
    protected LocalTime at = null;

    /**
     * Something.
     *
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        tag = EVENT_TAG;
        String[] atSplit = at.split(" ", 2);
        date = LocalDate.parse(atSplit[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (atSplit.length > 1) {
            this.at = LocalTime.parse(atSplit[1], DateTimeFormatter.ofPattern(TIME_FORMAT));
        }
    }

    /**
     * Something.
     *
     * @param description
     */
    public Event(String description) {
        super(description);
        tag = EVENT_TAG;
    }

    /**
     * Something.
     *
     * @param taskDescriptions
     * @throws DukeException
     */
    public Event(String ... taskDescriptions) throws DukeException {
        super(taskDescriptions[2]);
        tag = EVENT_TAG;
        if (taskDescriptions.length == 3) {
        } else if (taskDescriptions.length == 5) {
            this.date = LocalDate.parse(taskDescriptions[3]);
            this.at = LocalTime.parse(taskDescriptions[4]);
        } else {
            throw new DukeException("Task loading error...");
        }
        if (taskDescriptions[1].equals(TICK_BINARY)) {
            this.markAsDone();
        }
    }

    /**
     * Something.
     *
     * @return
     */
    public String toPrint() throws DukeException {
        if (date == null && at == null) {
            return super.toPrint();
        } else if (date != null && at != null) {
            return super.toPrint() + "|" + date + "|" + at;
        } else {
            throw new DukeException("DateTime Error la oi...");
        }
    }

    @Override
    public String toString() {
        if (at == null) {
            return "[" + tag + "] " + super.toString();
        } else {
            return "[" + tag + "] " + super.toString() + "\n            (by: "
                    + date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT))
                    + ", " + at.format(DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT)) + ")";
        }
    }
}
