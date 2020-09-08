package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.model.NekoHistory;
import nekochan.storage.Storage;
import nekochan.util.Messages;

/**
 * The {@code RedoCommand} class represents a command that allows the user to redo an undone {@code command}.
 */
public class RedoCommand extends Command {

    private static final boolean IS_EXIT = false;
    private static final boolean IS_UNDOABLE = false;

    /**
     * Executes this {@code RedoCommand} and saves the new state to storage.
     * @param history the currently loaded {@link NekoHistory} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public void execute(NekoHistory history, Storage storage) {
        history.redo();
        history.save(storage);
        super.isCompleted = true;
    }

    /**
     * Returns a {@link Response} from the execution of this {@code RedoCommand}.
     *
     * @return a {@code Response} object containing the result of executing this {@code RedoCommand}.
     * @throws IncompleteNekoCommandException if this {@code RedoCommand} was not executed.
     */
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_REDO_COMMAND);
        }
        String responseMessage = Messages.MESSAGE_REDO;
        return new Response(IS_EXIT, responseMessage);
    }
}
