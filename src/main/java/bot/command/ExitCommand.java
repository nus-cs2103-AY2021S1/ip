package bot.command;

import bot.Storage;
import bot.TaskList;

/**
 * A type of command that indicates the termination of the program.
 */
public class ExitCommand extends Command{
    public ExitCommand(String cmd) {
        super(cmd);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    /**
     * Bids farewell to the user.
     *
     * @param taskList taskList of the bot to be terminated.
     * @param storage storeage of the bot to be terminated.
     * @return Farewell message to the user.
     */
    @Override
    public String run(TaskList taskList, Storage storage) {
        return "Have a good day, mate!";
    }
}
