package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subtype of TaskWithDateTime in which the date time provided
 * is a looming deadline. Example:
 *     [D][✘] assignment (by: Aug 26 2020, 11:59 pm)
 */
public class Deadline extends TaskWithDateTime {

    /**
     * Constructs the deadline.
     * @param description the description of the task.
     * @param by the raw string of the date time.
     * @throws DukeException when task creation fails.
     */
    public Deadline(String description, String by) throws DukeException {
        super("D", description, by);
    }

    /**
     * Constructs the deadline with its completion status.
     * @param description the description of the task.
     * @param by the raw string of the date time.
     * @param isDone the completion status.
     * @throws DukeException when task creation fails.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super("D", description, by, isDone);
    }

    /**
     * Formats the string representation of the task and returns it.
     * @return the formatted string representation of the task.
     */
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
