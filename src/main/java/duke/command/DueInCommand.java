package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DueInCommand extends Command {
    private long time;
    private boolean hours;

    /**
     * Creates a DueInCommand with the given time.
     *
     * @param time The given time.
     * @param hours Boolean to check if time is in hours or days.
     */
    public DueInCommand(long time, boolean hours) {
        this.time = time;
        this.hours = hours;
    }

    /**
     * Provides the user with a list of tasks in the TaskList that are due in time hours.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (hours) {
            return(tasks.extractDueTasksHours(time));
        } else {
            return(tasks.extractDueTasksDays(time));
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DueInCommand;
    }
}
