package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeInvalidDayException;
import duke.exception.DukeInvalidTaskException;

/**
 * The Event class is used to represent the task of an event nature.
 * This object contains the task name as well as the day/date of the event.
 * Inherits from Task class.
 */

public class Event extends Task {
    private String day;

    /**
     * Initializes the Event object
     *
     * @param taskName name or description of task
     * @param day day in which task has to be completed
     * @throws DukeInvalidDayException
     * @throws DukeInvalidTaskException
     */

    public Event(String taskName, String day) throws DukeInvalidDayException, DukeInvalidTaskException {
        super(taskName);
        assert taskName != null : "TaskName should not be null!";
        assert day != null : "Day should not be null!";
        assert !day.equals(" ");
        repeatedFrequency = Frequency.NONE;
        if (!day.equals(null) && !day.equals(" ")) {
            this.day = day;
            try {
                super.dateTime = LocalDate.parse(day);
            } catch (DateTimeParseException err) {
                System.out.println("for dates, please input the date in yyyy-mm-dd format");
            }
        } else {
            throw new DukeInvalidDayException();
        }
    }

    /**
     * Initializes the Event object
     * @param taskName name or description of task
     * @param day day in which task has to be completed
     * @param frequency frequency to repeat task
     * @throws DukeInvalidDayException
     * @throws DukeInvalidTaskException
     */
    public Event(String taskName, String day, String frequency)
            throws DukeInvalidDayException, DukeInvalidTaskException {
        super(taskName);
        assert taskName != null : "TaskName should not be null!";
        assert day != null : "Day should not be null!";
        assert !day.equals(" ");
        repeatedFrequency = translateToFrequency(frequency);
        setIsRepetitive();
        if (!day.equals(null) && !day.equals(" ")) {
            this.day = day;
            try {
                super.dateTime = LocalDate.parse(day);
            } catch (DateTimeParseException err) {
                System.out.println("for dates, please input the date in yyyy-mm-dd format");
            }
        } else {
            throw new DukeInvalidDayException();
        }
    }
    /**
     * Gets the date of the Event
     *
     * @return a String representing the day.
     */
    @Override
    public String getDate() {
        if (dateTime != null) {
            return dateTime.toString();
        } else {
            return day;
        }
    }
    /**
     * Get a string representation of the object
     *
     * @return a String representing the Event object
     */


    @Override
    public String toString() {
        String finished = this.isDone ? "✓" : "✗";
        String frequency = isRepetitive
                                ? ", repeats " + getFrequency()
                                : ", does not repeat";
        String toReturn = dateTime == null
                            ? "[E]" + "[" + finished + "] " + taskName + " (at: " + day + ")"
                            : "[E]" + "[" + finished + "] " + taskName + " (at: "
                                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                                + frequency + ")";
        return toReturn;
    }
}
