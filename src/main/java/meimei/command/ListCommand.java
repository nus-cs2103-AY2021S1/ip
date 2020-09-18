package meimei.command;

import meimei.Storage;
import meimei.TaskList;
import meimei.Ui;
import meimei.botexception.BotException;

/**
 * Command that displays the user's list of tasks when executed.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws BotException {
        return ui.returnListReply(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
