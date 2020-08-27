/**
 * Represents a command to terminate the Duke chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes command to display the goodbye message.
     *
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    public boolean isExit() {
        return true;
    }
}
