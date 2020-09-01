package java.commands;

import java.tasklist.TaskList;
import java.storage.Storage;
import java.ui.Ui;

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
