package duke.task;

import duke.util.DukeException;

import java.time.format.DateTimeFormatter;

/**
 * Event is a subtype of TaskWithDateTime which requires an attendance at a fixed time.
 * Example:
 *
 *     [E][✘] birthday celebration (at: Mar 7 2020, 08:00 pm)
 */
public class Event extends TaskWithDateTime {

    public Event(String description, String at) throws DukeException {
        super("E", description, at);
    }

    public Event(String description, String at, boolean isDone) throws DukeException {
        super("E", description, at, isDone);
    }

    @Override
    public String toString() {
        String dt = String.format(" (at: %s",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (time.isPresent()) {
            dt += String.format(", %s)",
                    time.get().format(DateTimeFormatter.ofPattern("hh:mm a")));
        } else {
            dt += ")";
        }
        return super.toString() + dt;
    }
}
