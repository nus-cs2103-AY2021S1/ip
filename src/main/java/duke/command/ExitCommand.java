package duke.command;

import duke.exceptions.IncompleteDukeCommandException;
import duke.storage.Storage;
import duke.task.TaskList;

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
        super.completed = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code ExitCommand}.
     *
     * @throws IncompleteDukeCommandException if this {@code ExitCommand} was not executed.
     */
    public String feedback() throws IncompleteDukeCommandException {
        if (super.completed) {
            return "Ja ne!\n";
        } else {
            throw new IncompleteDukeCommandException("Exit command was not completed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
