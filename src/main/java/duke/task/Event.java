package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Represents an event object that has the event description and time of event.
 */
public class Event extends Task {
    private LocalDateTime timing;

    /**
     * Constructor to create an event object.
     *
     * @param detail is the description entered by the user.
     * @param timing is the timing for the event entered by the user
     * @throws DukeException if the details entered by the user is invalid.
     */
    public Event(String detail, LocalDateTime timing) throws DukeException {
        super(detail);
        this.timing = timing;
    }

    /**
     * Constructor that is overloaded to create a deadline object from the database.
     *
     * @param doneStatus the state of the task from the user's previous session
     * @param detail the description of the event
     * @param timing the time of the event
     */
    public Event(int doneStatus, String detail, LocalDateTime timing) {
        super(doneStatus, detail);
        this.timing = timing;
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.getDoneStatus() ? 1 : 0;
        return "E|" + status + "|" + super.description + "|" + timing;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " " + super.description + " (at:"
                + timing.format(dateTimeFormatter) + ")";
    }
}
