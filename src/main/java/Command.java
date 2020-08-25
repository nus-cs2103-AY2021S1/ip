abstract class Command {

    // Abstract Methods

    /**
     * Executes the command.
     * @param tasks TaskList representing list of current tasks.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    abstract boolean isExit();

}
