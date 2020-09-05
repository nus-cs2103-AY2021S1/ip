package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.storage.Storage;
import nekochan.task.TaskList;
import nekochan.util.Messages;

/**
 * The {@code Exit} class represents a command to safely terminate the program.
 */
public class ExitCommand extends Command {

    private static final boolean IS_EXIT = true;

    /**
     * Executes this {@code ExitCommand} by saving the specified {@code list} to file.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public void execute(TaskList list, Storage storage) {
        storage.save(list);
        super.isCompleted = true;
    }

    /**
     * Returns a {@link Response} from the execution of this {@code ExitCommand}.
     *
     * @return a {@code Response} object containing the result of executing this {@code ExitCommand}.
     * @throws IncompleteNekoCommandException if this {@code ExitCommand} was not executed.
     */
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_EXIT_COMMAND);
        }
        String responseMessage = Messages.MESSAGE_EXIT;
        return new Response(IS_EXIT, responseMessage);
    }
}
