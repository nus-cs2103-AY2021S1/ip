package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DueInCommand extends Command {
    private long time;
    private boolean hours;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (hours) {
            ui.say(tasks.extractDueTasksHours(time));
        } else {
            ui.say(tasks.extractDueTasksDays(time));
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DueInCommand;
    }
}
