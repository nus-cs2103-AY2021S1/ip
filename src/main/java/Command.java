import java.util.ArrayList;

/**
 * A Command is a parsed request from the user.
 */
public abstract class Command {

    /**
     * Modifies tasks and storage of the Duke instance depending on the type of Command.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be activated if there are any changes to TaskList.
     * @throws DukeException
     */
    public abstract ArrayList<String> execute(TaskList tasks, Storage storage) throws DukeException, TaskException;

    /**
     * Returns false for all commands except ExitCommand.
     *
     * @return Whether Command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
