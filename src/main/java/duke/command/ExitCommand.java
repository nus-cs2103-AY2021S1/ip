package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand implements Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveTaskList(tasks);
        storage.finalise();
        ui.exit();
    }

    public boolean isExit() {
        return true;
    }
}
