package duke;

/**
 * Command to quit Duke
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Exits program
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0);
        return ui.bye();
    }
}

