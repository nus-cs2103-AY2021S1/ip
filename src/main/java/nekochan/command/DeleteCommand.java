package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.model.NekoHistory;
import nekochan.storage.Storage;
import nekochan.task.Task;
import nekochan.task.TaskList;
import nekochan.util.Messages;

/**
 * The {@code DeleteCommand} class represents a command to delete a {@link Task} in a {@link TaskList}.
 */
public class DeleteCommand extends Command {

    private static final boolean IS_EXIT = false;

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
     * @param history the currently loaded {@link NekoHistory} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(NekoHistory history, Storage storage) {
        deletedTask = history.deleteTask(index);
        remainingTaskCount = history.getCurrentTaskCount();
        history.save(storage);
        super.isCompleted = true;
    }

    /**
     * Returns a {@link Response} from the execution of this {@code DeleteCommand}.
     *
     * @return a {@code Response} object containing the result of executing this {@code DeleteCommand}.
     * @throws IncompleteNekoCommandException if this {@code DeleteCommand} was not executed.
     */
    @Override
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_DELETE_COMMAND);
        }

        assert deletedTask != null : "deleted task should not be null";

        String responseMessage = Messages.MESSAGE_DELETE + deletedTask.toString() + "\n"
                + Messages.getTotalTaskMessage(remainingTaskCount);
        return new Response(IS_EXIT, responseMessage);
    }
}
