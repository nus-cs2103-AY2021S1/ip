package duke;

/**
 * Exits the programme.
 */
public class ExitCommand extends Command {

    /**
     * Executes command by exiting the Duke Programme.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage To read and write to file.
     * @param ui Interact with user.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showExitMessage();
    }

    /**
     * Returns the boolean value to exit the programme.
     *
     * @return Boolean to indicate end of programme.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
