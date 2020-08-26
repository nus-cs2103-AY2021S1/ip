package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.getCommandList();
    }
}
