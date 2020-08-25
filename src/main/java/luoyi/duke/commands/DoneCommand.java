package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.Duke;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;

/**
 * DoneCommand class to encapsulate a done command.
 * A done command marks done a task at a specific index.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class DoneCommand extends Command {

    private DoneCommand(int targetIndex, IDuke duke) {
        super(targetIndex, duke);
    }

    /**
     * Returns a new done command.
     *
     * @param targetIndex Index of task which will be removed.
     * @return DoneCommand object with specified properties, not yet initiated with duke.
     */
    public static DoneCommand getDoneCommand(int targetIndex) {
        return new DoneCommand(targetIndex, null);
    }

    /**
     * Executes the done command.
     * Duke object duke must be initiated.
     *
     * @return Resultant duke object.
     */
    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleDone(targetIndex);
    }

    /**
     * Handles done command.
     * Marks the task at index {@code index} as done.
     *
     * @param index Index of task to be marked done.
     * @return Resultant duke object.
     * @throws DukeIllegalArgumentException If index is invalid.
     */
    private IDuke handleDone(int index) throws DukeIllegalArgumentException {
        if (index < 1 || index > duke.getNumTask()) {
            throw new DukeIllegalArgumentException("Task index out of bound!");
        }
        IDuke newDuke = doneTask(index);
        System.out.print(TextFormatter.getFormattedText(
                Message.MARKED_DONE.toString() + newDuke.getTask(index)));
        newDuke.getStorage().save(newDuke.getTasks().getList());
        return newDuke;
    }

    /**
     * Marks a specified task as done.
     * Task to be done is specified by its index id.
     *
     * @return Duke with task done.
     * @throws DukeIllegalArgumentException If index is invalid.
     */
    private IDuke doneTask(int id) throws DukeIllegalArgumentException {
        TaskList list = duke.getTasks();
        Storage storage = duke.getStorage();
        if (id - 1 > list.size() || id < 0) {
            throw new DukeIllegalArgumentException(
                    "Cannot done task! Task id out of bound!");
        } else if (list.get(id - 1).isDone()) {
            throw new DukeIllegalArgumentException(
                    "Cannot done task! Task is already done!");
        }
        TaskList newList = new TaskList(list.getList());
        newList.replace(id - 1, newList.get(id - 1).markComplete());
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new DoneCommand(targetIndex, duke);
    }
}
