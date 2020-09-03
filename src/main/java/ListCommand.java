/**
 * Represents a command by the user to display their list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by displaying the list of tasks.
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If there are no tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        return ui.showList() + "\n" + tasks.printList();
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
