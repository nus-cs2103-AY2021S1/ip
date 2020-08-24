package main.java.Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.commandList();
    }
}
