package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subtype of Task which has a stipulated deadline for the task
 * to be completed by. Example:
 *
 *     [D][✘] assignment (by: Aug 26 2020, 11:59 pm)
 */
public class Deadline extends TaskWithDateTime {

    public Deadline(String description, String by) throws DukeException {
        super("D", description, by);
    }

    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super("D", description, by, isDone);
    }

    @Override
    public String toString() {
        String pattern = (date.getYear() == LocalDate.now().getYear() ? "d MMM" : "d MMM yy");
        String dt = String.format(" (by: %s", date.format(DateTimeFormatter.ofPattern(pattern)));
        if (time.isPresent()) {
            dt += String.format(", %s)",
                    time.get().format(DateTimeFormatter.ofPattern("h:mm a")));
        } else {
            dt += ")";
        }
        return super.toString() + dt;
    }
}
