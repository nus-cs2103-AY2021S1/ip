package duke.command;

import duke.logic.Tasklist;
import duke.logic.Storage;

/**
 * Represents a Command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Creates a Command to exit Duke.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Exits Duke.
     *
     * @param tasks The task list.
     * @param storage The Storage object that saves the task list.
     * @return An empty String.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        System.exit(0);
        return "";
    }

    /**
     * Returns true as it is an instance of an ExitCommand.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
