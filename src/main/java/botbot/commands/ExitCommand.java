package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.exit();
    }
    
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
