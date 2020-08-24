package main.java.Command;

import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.commandList();
    }
}
