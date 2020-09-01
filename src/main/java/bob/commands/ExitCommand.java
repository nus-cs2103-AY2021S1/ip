package bob.commands;

import bob.common.MsgGenerator;
import bob.data.task.Tasklist;
import bob.storage.Storage;

/**
 * Exits and cease Bob.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExited() {
        return true;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return MsgGenerator.generateExitMessage();
    }
}
