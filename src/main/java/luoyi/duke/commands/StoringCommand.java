package luoyi.duke.commands;

import luoyi.duke.data.IDuke;
import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;

/**
 * Abstract StoringCommand which supports storing tasks.
 */
public abstract class StoringCommand extends Command {
    /**
     * Returns a new Command.
     * @param targetIndex Target index for operation by the command.
     * @param duke        Duke object to perform action on.
     */
    protected StoringCommand(int targetIndex, IDuke duke) {
        super(targetIndex, duke);
    }

    /**
     * Adds task in Duke object.
     * Also invokes storage class to store task list on disk.
     *
     * @param task The tasks to be stored.
     */
    protected void storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList list = duke.getTasks();
        list.add(task);
        storage.save(list.getList());
    }

}
