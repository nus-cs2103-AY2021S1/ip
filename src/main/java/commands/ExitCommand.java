package main.java.commands;

import main.java.data.TaskList;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showErrorMessage("Bye, hope to see you again soon.");
        System.exit(0);
    }
}
