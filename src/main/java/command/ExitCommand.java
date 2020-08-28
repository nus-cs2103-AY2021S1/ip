package command;

import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents the exit command. The exit command stops execution of the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command. The execution involves exiting the program and showing the goodbye message.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     */
    public void execute(TaskList lst, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
