package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command {
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return null;
    }
}
