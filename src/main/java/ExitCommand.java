/**
 * Represents a command to exit from the program.
 */
public class ExitCommand extends Command {

    /**
     * Shows the goodbye message to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns that this is an exit program command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
