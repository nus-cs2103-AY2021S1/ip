/**
 * An exit command
 */
class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    ExitCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.exit = true;
    }

    /**
     * Executes the ExitCommand
     * @return Returns String goodbye message
     */
    @Override
    public String execute() {
        return "Jarvis is exiting, goodbye!";
    }
}
