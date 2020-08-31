package duke.commands;

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
        boolean dateExists = false;
        String response = "";
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).hasDate(description)) {
                    response += i.toString() + "\n";
                    dateExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).hasDate(description)) {
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
