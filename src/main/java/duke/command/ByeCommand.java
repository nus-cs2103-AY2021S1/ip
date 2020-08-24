package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
            ui.end();
        } catch (DukeException e) {
            ui.say(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
