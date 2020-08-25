package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.isExit = true;
        storage.Save(tasks.convertToFile());
        ui.showExit();
    }
}
