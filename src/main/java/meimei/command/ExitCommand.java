package meimei.command;

import meimei.Storage;
import meimei.TaskList;
import meimei.Ui;
import meimei.botexception.BotException;

/**
 * Command that displays a goodbye message and exits(quits) the bot when executed.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws BotException {
        return ui.returnExitReply();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
