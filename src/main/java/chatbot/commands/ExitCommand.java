package main.java.commands;

import main.java.data.TaskList;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.errorMessage("Bye, hope to see you again soon.");
    }
}
