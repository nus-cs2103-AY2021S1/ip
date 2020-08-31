package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
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
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException if tasks with time is not in tasklist.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        boolean timeExists = false;
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).hasTime(description)) {
                    ui.showMessage(i.toString());
                    timeExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).hasTime(description)) {
                    ui.showMessage(i.toString());
                    timeExists = true;
                }
            }
        }
        if (!timeExists) {
            throw new DukeException("No events/deadlines with this time!");
        }
    }
}
