package nekochan.command;

import java.util.ArrayList;
import java.util.List;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.storage.Storage;
import nekochan.task.Task;
import nekochan.task.TaskList;
import nekochan.util.Messages;

/**
 * The {@code ListCommand} class represents a command to print all contents of a {@link TaskList}.
 * The output of the {@code ListCommand} retains the same order of tasks in the {@code TaskList}.
 */
public class ListCommand extends Command {

    private static final boolean IS_EXIT = false;

    private List<Task> existingTasks;

    /**
     * Executes this {@code ListCommand}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        existingTasks = new ArrayList<>();
        for (Task task : list) {
            existingTasks.add(task);
        }
        super.isCompleted = true;
    }

    /**
     * Returns a {@link Response} from executing this {@code ListCommand}.
     *
     * @return a {@code Response} object containing the result of executing this {@code ListCommand}.
     * @throws IncompleteNekoCommandException if this {@code ListCommand} was not executed.
     */
    @Override
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_LIST_COMMAND);
        }
        String responseMessage = "";
        if (existingTasks.size() == 0) {
            responseMessage = Messages.MESSAGE_EMPTY_LIST;
        } else {
            responseMessage = Messages.MESSAGE_LIST;
            for (int i = 0; i < existingTasks.size(); i++) {
                responseMessage += String.format("%d. %s\n", i + 1, existingTasks.get(i).toString());
            }
        }

        assert responseMessage.length() > 0 : "response message should not be empty";

        return new Response(IS_EXIT, responseMessage);
    }
}
