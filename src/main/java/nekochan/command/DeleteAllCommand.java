package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.model.NekoHistory;
import nekochan.storage.Storage;
import nekochan.task.TaskList;
import nekochan.util.Messages;

/**
 * The {@code DeleteAllCommand} class represents a command to remove all entries in a {@link TaskList}.
 */
public class DeleteAllCommand extends Command {

    private static final boolean IS_EXIT = false;

    /**
     * Executes this {@code DeleteAllCommand}.
     * Deletes all contents in the specified {@code list}.
     *
     * @param history the currently loaded {@link NekoHistory} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(NekoHistory history, Storage storage) {
        history.clearAllTasks();
        history.save(storage);
        super.isCompleted = true;
    }

    /**
     * Returns a {@link Response} from the execution of this {@code DeleteAllCommand}.
     *
     * @return @return a {@code Response} object containing the result of executing this {@code DeleteAllCommand}.
     * @throws IncompleteNekoCommandException if this {@code DeleteAllCommand} was not executed.
     */
    @Override
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_DELETE_ALL_COMMAND);
        }
        String responseMessage = Messages.MESSAGE_DELETE_ALL;
        return new Response(IS_EXIT, responseMessage);
    }
}
