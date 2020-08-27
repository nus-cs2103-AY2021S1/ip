/**
 * Represents a command by the user to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by showing the exit message to the user.
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Returns true as the command is an exit command.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
