package command;

import command.Command;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
