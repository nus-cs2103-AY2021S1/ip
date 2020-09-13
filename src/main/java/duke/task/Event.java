package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Type of Task which involves a starting date and time.
 */
public class Event extends Task {
    /** Starting date object of the Event */
    protected LocalDate atDate;
    /** Starting time object of the Event */
    protected LocalTime atTime;

    /**
     * Constructor to create a new Event object with the description and starting date and time.
     *
     * @param description details of the Event Task.
     * @param at starting Date and Time of the Event Task.
     * @throws DukeException when there is no date and time inputted for the Event,
     *  or if the date and time are in the wrong format.
     */
    public Event (String description, String at) throws DukeException {
        super(description);

        try {
            this.atDate = formatDate(at);
            this.atTime = formatTime(at);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    /**
     * Overloaded constructor used when Tasks are being added from the user's local storage to Duke's TaskList.
     *
     * @param description details of the Event Task.
     * @param isDone determines if the Event has been completed or not.
     * @param at starting Date and Time of the Event Task.
     * @throws DukeException when there is no date and time inputted for the Event,
     *  or if the date and time are in the wrong format.
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.atDate = formatDate(at);
            this.atTime = formatTime(at);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    private Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    private LocalTime formatTime(String at) {
        int timeIndex = at.indexOf(" ") + 1;
        String timePortion = at.substring(timeIndex);
        LocalTime time = LocalTime.parse(timePortion);
        return time;
    }

    private LocalDate formatDate(String at) {
        int dateIndex = at.indexOf(" ");
        String datePortion = at.substring(0, dateIndex);
        String formattedDate = datePortion.replaceAll("/", "-");
        LocalDate date = LocalDate.parse(formattedDate);
        return date;
    }

    /**
     * Creates a deep copy of the current Event.
     *
     * @return a copy of the current Event.
     */
    @Override
    public Event deepCopy() {
        return new Event(this.description, this.atDate, this.atTime);
    }

    /**
     * Prints to user the description, and formatted date and time of the Event Task.
     *
     * @return <code>String</code> representing the formatted Event Task details.
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.atTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
