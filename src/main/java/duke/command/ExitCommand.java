package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.exitMessage();
    }
}
