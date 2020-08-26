package dude.command;

import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String action) {
        super(action);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.sendOff();
    }
}
