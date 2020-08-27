/**
 * Represents a command that can be executed.
 */
abstract class Command {

    /**
     * Execute the command that user calls.
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     * @throws DukeException if unable to update file to storage's filepath when required
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();

}
