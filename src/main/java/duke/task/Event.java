package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subtype of TaskWithDateTime in which the provided date time
 * is the occurrence of the event. Example:
 *     [E][✘] birthday celebration (at: Mar 7 2020, 08:00 pm)
 */
public class Event extends TaskWithDateTime {

    /**
     * Constructs the event.
     * @param description the description of the task.
     * @param at the raw string of the date time.
     * @throws DukeException when task creation fails.
     */
    public Event(String description, String at) throws DukeException {
        super("E", description, at);
    }

    /**
     * Constructs the event with the completion status.
     * @param description the description of the task.
     * @param at the raw string of the date time.
     * @param isDone the completion status.
     * @throws DukeException when task creation fails.
     */
    public Event(String description, String at, boolean isDone) throws DukeException {
        super("E", description, at, isDone);
    }

    /**
     * Formats the string representation of the task and returns it.
     * @return the formatted string representation of the task.
     */
    @Override
    public String toString() {
        String pattern = (date.getYear() == LocalDate.now().getYear() ? "d MMM" : "d MMM yy");
        String dt = String.format(" (at: %s", date.format(DateTimeFormatter.ofPattern(pattern)));
        if (time.isPresent()) {
            dt += String.format(", %s)",
                    time.get().format(DateTimeFormatter.ofPattern("h:mm a")));
        } else {
            dt += ")";
        }
        return super.toString() + dt;
    }
}
