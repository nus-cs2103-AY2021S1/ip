package duke;

/**
 * Controls logic of listing tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes list tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.listTasks();
        return ui.printOutput(output);
    }

    /**
     * Checks if should exit program.
     *
     * @return Should not exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
