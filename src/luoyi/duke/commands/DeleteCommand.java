package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.Duke;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;

public class DeleteCommand extends Command {
    private DeleteCommand(int targetIndex, IDuke duke) {
        super(targetIndex, duke);
    }

    /**
     * Factory method for getting DeleteCommand.
     *
     * @param targetIndex Index of task to be deleted.
     * @return DeleteCommand object.
     */
    public static DeleteCommand getDeleteCommand(int targetIndex) {
        return new DeleteCommand(targetIndex, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleDelete(targetIndex);
    }

    private IDuke handleDelete(int index) {
        if (index < 1 || index > duke.getNumTask()) {
            throw new DukeIllegalArgumentException("Task index out of bound!");
        }
        IDuke newDuke = deleteTask(index);
        System.out.println(TextFormatter.getFormattedText(
                Message.REMOVED_TASK.toString() + "\n\t" + duke.getTask(index)
                + "\n Now you have " + newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    /**
     * Removes a specified task.
     * Task to be removed is specified by its index id.
     *
     * @return Duke with task removed.
     * @throws DukeIllegalArgumentException If index out of bound.
     */
    private IDuke deleteTask(int id) throws DukeIllegalArgumentException {
        TaskList list = duke.getTasks();
        Storage storage = duke.getStorage();
        if (id - 1 > list.size() || id < 0) {
            throw new DukeIllegalArgumentException(
                    "Cannot delete task! Task id out of bound!");
        }
        TaskList newList = new TaskList(list.getList());
        newList.remove(id - 1);
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command setDuke(IDuke duke) {
        return new DeleteCommand(targetIndex, duke);
    }
}
