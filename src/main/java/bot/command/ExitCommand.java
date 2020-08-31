package bot.command;

import bot.Storage;
import bot.TaskList;

public class ExitCommand extends Command{
    public ExitCommand(String cmd) {
        super(cmd);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return "Have a good day, mate!";
    }
}
