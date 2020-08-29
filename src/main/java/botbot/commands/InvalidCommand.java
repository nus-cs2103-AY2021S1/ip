package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

public class InvalidCommand extends Command {
    private String error;
    
    public InvalidCommand(String error) {
        this.error = error;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printStatus(error);
    }
}
