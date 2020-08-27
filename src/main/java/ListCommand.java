/**
 * Represents a command by the user to display their list of tasks.
 */
public class ListCommand extends Command {
    /**
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If there are no tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Task.totalTasks == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        ui.showList();
        tasks.printList();
    }

    /**
     * Returns false as the command is not an exit command.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
