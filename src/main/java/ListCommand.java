package main.java;

/**
 * This command when executed prints out all tasks on a TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by calling on the UI to print out the contents of a TaskList.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @throws BobIndexOutOfBoundsException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIndexOutOfBoundsException {
        ui.printOutList(tasks);
    }
}
