package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.model.NekoHistory;
import nekochan.storage.Storage;
import nekochan.util.Messages;

/**
 * The {@code UndoCommand} class represents a command that allows users to undo a {@code Command}.
 */
public class UndoCommand extends Command {

    private static final boolean IS_EXIT = false;
    private static final boolean IS_UNDOABLE = false;

    /**
     * Executes this {@code UndoCommand} and saves the new state to file.
     *
     * @param history the currently loaded {@link NekoHistory} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public void execute(NekoHistory history, Storage storage) {
        history.undo();
        history.save(storage);
        super.isCompleted = true;
    }

    /**
     * Returns a {@link Response} from the execution of this {@code UndoCommand}.
     *
     * @return a {@code Response} object containing the result of executing this {@code UndoCommand}.
     * @throws IncompleteNekoCommandException if this {@code UndoCommand} was not executed.
     */
    public Response getResponse() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_UNDO_COMMAND);
        }
        String responseMessage = Messages.MESSAGE_UNDO;
        return new Response(IS_EXIT, responseMessage);
    }
}
