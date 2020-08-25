package main.java.commands;

import main.java.commands.Command;
import main.java.tasklist.TaskList;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        ui.displayBye();
    }
}
