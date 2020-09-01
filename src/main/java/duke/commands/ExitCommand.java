package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        String output = ui.displayBye();
        return output;

    }
}
