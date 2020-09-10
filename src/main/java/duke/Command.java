package duke;


/**
 * Abstract class for all commands
 */
public abstract class Command {
    private final boolean isExit;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }


    /**
     * Executes command and returns output string
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @return String to be output by Duke in the dialogue box
     * @throws DukeException if exception encountered
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "";
    }

    public boolean isExit() {
        return isExit;
    }
}
