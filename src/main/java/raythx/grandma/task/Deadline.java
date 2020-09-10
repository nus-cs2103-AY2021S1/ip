package raythx.grandma.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import raythx.grandma.exception.DateTimeException;
import raythx.grandma.exception.DukeException;
import raythx.grandma.exception.ParseDateTimeException;
import raythx.grandma.exception.TaskLoadingException;

/**
 * Represents a specific task which has a deadline.
 */
public class Deadline extends Task {

    protected LocalDate date = null;
    protected LocalTime time = null;

    /**
     * Constructor for Deadline.
     *
     * @param description task description.
     * @param period date and time of task.
     * @throws ParseDateTimeException
     */
    public Deadline(String description, String taskTag, String period) throws ParseDateTimeException {
        super(description, taskTag);
        tag = DEADLINE_TAG;
        String[] splitPeriod = period.split(" ", 2);
        try {
            date = LocalDate.parse(splitPeriod[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
            if (splitPeriod.length > 1) {
                this.time = LocalTime.parse(splitPeriod[1], DateTimeFormatter.ofPattern(TIME_FORMAT));
            }
        } catch (DateTimeParseException exception) {
            throw new ParseDateTimeException();
        }
    }

    /**
     * Constructor for Deadline.
     *
     * @param description task description.
     */
    public Deadline(String description, String taskTag) {
        super(description, taskTag);
        tag = DEADLINE_TAG;
    }

    /**
     * Constructor for Deadline.
     *
     * @param taskInformation a varargs of taskInformation.
     * @throws DukeException
     */
    public Deadline(String ... taskInformation) throws DukeException {
        super(taskInformation[2], taskInformation[3]);
        tag = DEADLINE_TAG;
        if (taskInformation.length == 4) {
            //Nothing else to handle
        } else if (taskInformation.length == 6) {
            this.date = LocalDate.parse(taskInformation[4]);
            this.time = LocalTime.parse(taskInformation[5]);
        } else {
            throw new TaskLoadingException();
        }
        if (taskInformation[1].equals(TICK_BINARY)) {
            this.markAsDone();
        }
    }

    /**
     * Encodes the task to be saved upon exit.
     *
     * @return encoded task.
     */
    public String encodeTask() throws DukeException {
        if (time == null) {
            assert date == null;
            return super.encodeTask();
        } else if (time != null) {
          assert date != null;
            return super.encodeTask() + "|" + date + "|" + time;
        } else {
            throw new DateTimeException();
        }
    }

    @Override
    public String toString() {
        if (time == null) {
            assert date == null;
            return "[" + tag + "] " + super.toString();
        } else {
            return "[" + tag + "] " + super.toString() + "\n            (by: "
                    + date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT))
                    + ", " + time.format(DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT)) + ")";
        }
    }
}
