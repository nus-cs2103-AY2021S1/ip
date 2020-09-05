package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command to find tasks by date.
 */
public class DateCommand extends Command {
    private String description;

    /**
     * Constructor to create DateCommand object.
     *
     * @param description date being searched
     */
    public DateCommand(String description) {
        this.description = description;
    }

    /**
     * Searches for the date that was keyed in.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException if tasks with date is not in tasklist.
     */
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        assert tasklist != null : "Tasklist cannot be null.";
        assert storage != null : "Storage cannot be null.";
        assert description != null : "Description cannot be null.";
        boolean dateExists = false;
        String response = "";
        LocalDate dateSearched;
        try {
            dateSearched = LocalDate.parse(description);
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid date. Put in format 'YYYY MM DD'.");
        }
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).isSameDate(dateSearched)) {
                    response += i.toString() + "\n";
                    dateExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).isSameDate(dateSearched)) {
                    response += i.toString() + "\n";;
                    dateExists = true;
                }
            }
        }
        if (!dateExists) {
            throw new DukeException("No events/deadlines with this date!");
        }
        return response;
    }
}
