package bot.command;

import bot.Storage;
import bot.TaskList;
import bot.util.InvalidInputException;

import java.io.IOException;

public abstract class Command {
    protected String cmd;

    public Command(String cmd) {
        this.cmd = cmd;
    }

    public boolean isExitCommand() {
        return false;
    }

    public abstract String run(TaskList taskList, Storage storage) throws
            InvalidInputException, IOException;
}
