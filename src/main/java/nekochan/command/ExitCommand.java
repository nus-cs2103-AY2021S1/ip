package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.storage.Storage;
import nekochan.task.TaskList;

/**
 * The {@code Exit} class represents a command to safely terminate the program.
 */
public class ExitCommand extends Command {

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
     * Prints a feedback confirming the execution of this {@code ExitCommand}.
     *
     * @throws IncompleteNekoCommandException if this {@code ExitCommand} was not executed.
     */
    public String feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException("Exit command was not completed.");
        }
        return "Ja ne!\n";
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
