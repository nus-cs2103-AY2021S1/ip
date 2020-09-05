package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.storage.Storage;
import nekochan.task.Task;
import nekochan.task.TaskList;
import nekochan.util.Messages;

/**
 * The {@code CompleteCommand} class represents a command to mark a {@link Task} in a {@link TaskList} as complete.
 */
public class CompleteCommand extends Command {

    private static final boolean IS_EXIT = false;

    private int index;
    private Task completedTask;

    /**
     * Constructs a {@code CompleteCommand} with the specified {@code index}.
     *
     * @param index the index of the {@code Task} to mark as complete.
     */
    public CompleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this {@code CompleteCommand}.
     * Marks the {@code Task} in the specified {@code list} at the stored {@code index} as complete.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        completedTask = list.markAsComplete(index);
        storage.save(list);
        super.isCompleted = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code CompleteCommand}.
     *
     * @throws IncompleteNekoCommandException if this {@code CompleteCommand} was not executed.
     */
    @Override
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_COMPLETE_COMMAND);
        }
        String responseMessage = Messages.MESSAGE_COMPLETE + completedTask.toString() + "\n";
        return new Response(IS_EXIT, responseMessage);
    }
}
