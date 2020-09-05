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
     * Prints the result of executing this {@code ListCommand}.
     *
     * @throws IncompleteNekoCommandException if this {@code ListCommand} was not executed.
     */
    @Override
    public String feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_LIST_COMMAND);
        }
        String printout = "";
        if (existingTasks.size() == 0) {
            printout = Messages.MESSAGE_EMPTY_LIST;
        } else {
            printout = Messages.MESSAGE_LIST;
            for (int i = 0; i < existingTasks.size(); i++) {
                printout += String.format("%d. %s\n", i + 1, existingTasks.get(i).toString());
            }
        }
        return printout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
