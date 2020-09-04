package raythx98.grandma.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import raythx98.grandma.exception.DukeException;

/**
 * Represents a specific task which is a to do.
 */
public class ToDo extends Task {

    protected LocalDate date = null;
    protected LocalTime by = null;

    /**
     * Something.
     *
     * @param description
     * @param by
     */
    public ToDo(String description, String by) {
        super(description);
        tag = TODO_TAG;
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (bySplit.length > 1) {
            this.by = LocalTime.parse(bySplit[1], DateTimeFormatter.ofPattern(TIME_FORMAT));
        }
    }

    /**
     * Something.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
        tag = TODO_TAG;
    }

    /**
     * Something.
     *
     * @param taskDescriptions
     * @throws DukeException
     */
    public ToDo(String ... taskDescriptions) throws DukeException {
        super(taskDescriptions[2]);
        tag = TODO_TAG;
        if (taskDescriptions.length == 3) {
        } else if (taskDescriptions.length == 5) {
            this.date = LocalDate.parse(taskDescriptions[3]);
            this.by = LocalTime.parse(taskDescriptions[4]);
        } else {
            throw new DukeException("Task loading error...");
        }
        if (taskDescriptions[1].equals(TICK_BINARY)) {
            this.markAsDone();
        }
    }

    /**
     * Something
     *
     * @return
     */
    public String toPrint() throws DukeException {
        if (date == null && by == null) {
            return super.toPrint();
        } else if (date != null && by != null) {
            return super.toPrint() + "|" + date + "|" + by;
        } else {
            throw new DukeException("DateTime Error la oi...");
        }
    }

    @Override
    public String toString() {
        if (by == null) {
            return "[" + tag + "] " + super.toString();
        } else {
            return "[" + tag + "] " + super.toString() + "\n            (by: "
                    + date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT))
                    + ", " + by.format(DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT)) + ")";
        }
    }
}
