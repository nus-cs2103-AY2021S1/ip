package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represent an exit command.
 */
public class ExitCommand extends Command{

    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Perform exit on the system.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws SparklesException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("     Bye. Hope to see you again!");
        ui.showLine();
        System.exit(0);
    }

    /**
     * Only command that returns true for this method.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
