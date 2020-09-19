package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidTaskException;


/**
 * The Deadline class is used to represent the task of a deadline nature.
 * This object contains the task name as well as the day/date of the deadline.
 * Inherits from Task class.
 */

public class Deadline extends Task {
    private String date;

    /**
     * Initializes a Deadline object
     *
     * @param taskName name or description of task
     * @param date date in which task has to be completed
     * @throws DukeInvalidDateException
     * @throws DukeInvalidTaskException
     */

    public Deadline(String taskName, String date) throws DukeInvalidDateException, DukeInvalidTaskException {
        super(taskName);
        assert taskName != null : "TaskName should not be null!";
        assert date != null : "Date should not be null!";
        assert !date.equals(" ");
        repeatedFrequency = Frequency.NONE;
        if (!date.equals(null) && !date.equals(" ")) {
            this.date = date;
            try {
                super.dateTime = LocalDate.parse(date);
            } catch (DateTimeParseException err) {
                System.out.println("for dates, please input the date in yyyy-mm-dd format");
            }
        } else {
            throw new DukeInvalidDateException();
        }
    }
    /**
     * Initializes a Deadline object that is repetitive
     * @param taskName name or description of task
     * @param date date in which task has to be completed
     * @param frequency frequency of the task to be repeated
     * @throws DukeInvalidDateException
     * @throws DukeInvalidTaskException
     */
    public Deadline(String taskName, String date, String frequency)
            throws DukeInvalidDateException, DukeInvalidTaskException {
        super(taskName);
        assert taskName != null : "TaskName should not be null!";
        assert date != null : "Date should not be null!";
        assert !date.equals(" ");
        assert frequency != null : "Frequency cannot be null!";
        repeatedFrequency = translateToFrequency(frequency);
        setIsRepetitive();
        if (!date.equals(null) && !date.equals(" ")) {
            this.date = date;
            try {
                super.dateTime = LocalDate.parse(date);
            } catch (DateTimeParseException err) {
                System.out.println("for dates, please input the date in yyyy-mm-dd format");
            }
        } else {
            throw new DukeInvalidDateException();
        }
    }

    /**
     * Gets the date of the Deadline
     *
     * @return a String representing the date.
     */
    @Override
    public String getDate() {
        if (dateTime == null) {
            return date;
        } else {
            return dateTime.toString();
        }
    }

    /**
     * Get a string representation of the object
     *
     * @return a String representing the Deadline object
     */

    @Override
    public String toString() {
        String finished = this.isDone ? "✓" : "✗";
        String frequency = isRepetitive
                ? ", repeats " + getFrequency()
                : ", does not repeat";
        String toReturn = dateTime == null
                            ? "[D]" + "[" + finished + "] " + taskName + " (by: " + date + ")"
                            : "[D]" + "[" + finished + "] " + taskName + " (by: "
                                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                                + frequency + ")";
        return toReturn;
    }
}
