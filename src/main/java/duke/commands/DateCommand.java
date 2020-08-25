package duke.commands;

import duke.*;
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
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException if tasks with date is not in tasklist.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        boolean dateExists = false;
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if(((Deadline) i).hasDate(description)) {
                    ui.showMessage(i.toString());
                    dateExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).hasDate(description)) {
                    ui.showMessage(i.toString());
                    dateExists = true;
                }
            }
        }
        if (!dateExists) {
            throw new DukeException("No events/deadlines with this date!");
        }
    }
}
