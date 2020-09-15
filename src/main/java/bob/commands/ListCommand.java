package bob.commands;

import bob.common.MsgGenerator;
import bob.data.task.Tasklist;
import bob.storage.Storage;

/**
 * Lists all tasks in Bob's tasklist.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return MsgGenerator.generateListMessage(tasks);
    }

}
