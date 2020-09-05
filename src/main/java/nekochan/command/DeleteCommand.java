package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.storage.Storage;
import nekochan.task.Task;
import nekochan.task.TaskList;
import nekochan.util.Messages;

/**
 * The {@code DeleteCommand} class represents a command to delete a {@link Task} in a {@link TaskList}.
 */
public class DeleteCommand extends Command {

    private int index;
    private Task deletedTask;
    private int remainingTaskCount;

    /**
     * Constructs a {@code DeleteCommand} with the specified {@code index}.
     *
     * @param index the index of the {@code Task} to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this {@code DeleteCommand}.
     * Deletes the {@code Task} in the specified {@code list} at the stored {@code index}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        deletedTask = list.deleteTask(index);
        remainingTaskCount = list.getTaskCount();
        storage.save(list);
        super.isCompleted = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code DeleteCommand}.
     *
     * @throws IncompleteNekoCommandException if this {@code DeleteCommand} was not executed.
     */
    @Override
    public String feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_DELETE_COMMAND);
        }
        return Messages.MESSAGE_DELETE + deletedTask.toString() + "\n"
                + Messages.getTotalTaskMessage(remainingTaskCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
