package duke.commands;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command to find tasks by time.
 */
public class TimeCommand extends Command {
    private String description;

    /**
     * Constructor to create TimeCommand object.
     *
     * @param description time being searched
     */
    public TimeCommand(String description) {
        this.description = description;
    }

    /**
     * Searches for the time that was keyed in.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException if tasks with time is not in tasklist.
     */
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        assert tasklist != null : "Tasklist cannot be null.";
        assert storage != null : "Storage cannot be null.";
        assert description != null : "Description cannot be null.";
        boolean timeExists = false;
        String response = "";
        LocalTime timeSearched;
        try {
            timeSearched = LocalTime.parse(description);
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid Time. Put in format 'HH:mm'.");
        }
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).isSameTime(timeSearched)) {
                    response += (i.toString()) + "\n";
                    timeExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).isSameTime(timeSearched)) {
                    response += (i.toString()) + "\n";
                    timeExists = true;
                }
            }
        }
        if (!timeExists) {
            throw new DukeException("No events/deadlines with this time!");
        }
        return response;
    }
}
