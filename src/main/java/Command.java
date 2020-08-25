/**
 * Represents an executable command that can be given by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks The current task list.
     * @param ui UI class for user interaction.
     * @param storage Storage class for saving task list.
     * @throws DukeException Throws DukeException if there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the program should be exited after executing this command.
     * @return Whether the program should be exited.
     */
    public boolean isExit() {
        return false;
    }
}
