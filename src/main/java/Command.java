/**
 * Represents a command that can be executed.
 */
abstract class Command {
    /**
     * Execute the command that user calls.
     *
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     * @return String message to show the executed event.
     * @throws DukeException If unable to update file to storage's filepath when required.
     */
    abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();
}
