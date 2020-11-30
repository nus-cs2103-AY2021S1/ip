package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;


/**
 * DeleteCommand class to encapsulate a deadline command.
 * A delete command deletes a task at a specific index.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class DeleteCommand extends Command {
    private DeleteCommand(int targetIndex, IDuke duke) {
        super(targetIndex, duke);
    }

    /**
     * Returns a DeleteCommand object.
     *
     * @param targetIndex Index of task to be deleted.
     * @return DeleteCommand object with specified properties, not yet initiated with duke.
     */
    public static DeleteCommand getDeleteCommand(int targetIndex) {
        return new DeleteCommand(targetIndex, null);
    }

    /**
     * Executes command and delete the task at targetIndex in duke.
     * Duke object duke must be first initiated.
     *
     * @return Result string.
     */
    @Override
    public String execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleDelete(targetIndex);
    }

    /**
     * Handles delete operation on duke.
     *
     * @param index Index of task to delete.
     * @return Result string.
     * @throws DukeIllegalArgumentException If index is invalid.
     */
    private String handleDelete(int index) throws DukeIllegalArgumentException {
        assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        if (index < 1 || index > duke.getNumTask()) {
            throw new DukeIllegalArgumentException("Task index out of bound!");
        }
        String output = Message.REMOVED_TASK.toString() + "\t" + duke.getTask(index)
                        + "\n Now you have " + (duke.getNumTask() - 1) + " task(s) in the list.";
        deleteTask(index);
        System.out.print(TextFormatter.getFormattedText(output));
        return output;
    }

    /**
     * Removes a specified task.
     * Task to be removed is specified by its index id.
     *
     * @throws DukeIllegalArgumentException If index out of bound.
     */
    private void deleteTask(int id) throws DukeIllegalArgumentException {
        assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        TaskList list = duke.getTasks();
        Storage storage = duke.getStorage();
        if (id - 1 > list.size() || id < 0) {
            throw new DukeIllegalArgumentException(
                    "Cannot delete task! Task id out of bound!");
        }
        list.remove(id - 1);
        storage.save(list.getList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command setDuke(IDuke duke) {
        return new DeleteCommand(targetIndex, duke);
    }
}
