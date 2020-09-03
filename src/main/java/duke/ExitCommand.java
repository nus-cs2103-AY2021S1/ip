package duke;

/**
 * Controls logic of exiting program.
 */
class ExitCommand extends Command {

    /**
     * Executes exit.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        return ui.showExit();
    }

    /**
     * Checks if should exit program.
     *
     * @return Should exit program.
     */
    @Override
    boolean isExit() {
        return true;
    }
}
