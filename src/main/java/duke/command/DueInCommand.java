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

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (hours) {
            ui.say(tasks.extractDueTasksHours(time));
        } else {
            ui.say(tasks.extractDueTasksDays(time));
        }
    }
}
