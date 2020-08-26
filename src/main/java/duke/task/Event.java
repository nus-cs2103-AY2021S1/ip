package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event object that has the event description and time of event.
 */
public class Event extends Task {
    private LocalDateTime timing;

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
        int status = super.isDone ? 1 : 0;
        return "E|" + status + "|" + super.description + "|" + timing;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " " + super.description + " (at:" + timing.format(dateTimeFormatter) + ")";
    }
}
