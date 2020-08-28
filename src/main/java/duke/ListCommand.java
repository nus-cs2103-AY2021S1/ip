package duke;

/**
 * Controls logic of listing tasks.
 */
class ListCommand extends Command {

    /**
     * Executes list tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.listTasks();
        ui.printOutput(output, false);
    }

    /**
     * Checks if should exit program.
     *
     * @return Should not exit program.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
