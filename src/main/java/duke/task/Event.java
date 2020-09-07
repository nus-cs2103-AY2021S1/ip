package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Represents an event object that has the event description and time of event.
 */
public class Event extends Task {
    private LocalDateTime eventTime;

    /**
     * Constructor to create an event object.
     *
     * @param detail is the description entered by the user.
     * @param eventTime is the eventTime for the event entered by the user
     * @throws DukeException if the details entered by the user is invalid.
     */
    public Event(String detail, LocalDateTime eventTime) throws DukeException {
        super(detail);
        this.eventTime = eventTime;
    }

    /**
     * Constructor that is overloaded to create a deadline object from the database.
     *
     * @param doneStatus the state of the task from the user's previous session
     * @param detail the description of the event
     * @param eventTime the time of the event
     */
    public Event(String priority, int doneStatus, String detail, LocalDateTime eventTime) {
        super(priority, doneStatus, detail);
        this.eventTime = eventTime;
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.getDoneStatus() ? 1 : 0;
        return super.priority.name() +"|" + "E|" + status + "|" + super.description + "|" + eventTime;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[" + super.priority.name() + "]" + "[E]" + super.toString() + " (At: "
                + eventTime.format(dateTimeFormatter) + ")";
    }
}
