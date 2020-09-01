package raythx98.duke.command;

import raythx98.duke.exception.DukeException;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showOnScreen("Here yur tasks faggit: \n" + tasks.toString());
    }
}
