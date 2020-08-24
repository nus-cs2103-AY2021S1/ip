package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Type of Task which includes a end date and end time.
 */
public class Deadline extends Task {
    /** Deadline date object of task */
    protected LocalDate byDate;
    /** Deadline time object of task */
    protected LocalTime byTime;

    /**
     * Constructor to create a Deadline task.
     *
     * @param description describes the details of the Deadline.
     * @param by the end date and time of the Deadline.
     * @throws DukeException when date and time inputs are invalid, or when no date and time is inputted.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = formatDate(by);
            this.byTime = formatTime(by);

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    /**
     * Overloaded constructor used when Tasks are being read from the local Storage of the user.
     *
     * @param description describes the details of the Deadline.
     * @param isDone determines whether task has been completed or not.
     * @param by the end date and time of the Deadline.
     * @throws DukeException when date and time inputs are invalid, or when no date and time is inputted.
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            this.byDate = formatDate(by);
            this.byTime = formatTime(by);

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    private LocalTime formatTime(String by) {
        String timePortion = by.substring(by.indexOf(" ") + 1);
        LocalTime time = LocalTime.parse(timePortion);
        return time;
    }

    private LocalDate formatDate(String by) {
        String datePortion = by.substring(0, by.indexOf(" ")).replaceAll("/", "-");
        LocalDate date = LocalDate.parse(datePortion);
        return date;
    }

    /**
     * Prints to user the description, and formatted date and time of the Deadline Task.
     *
     * @return <code>String</code> representing the formatted Deadline Task details.
     */
    @Override
    public String toString(){
        return "D | " + super.toString() + " | " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + this.byTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}