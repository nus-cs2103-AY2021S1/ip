package sparkles.command.addcommand;

import sparkles.SparklesException;
import sparkles.command.Command;
import sparkles.task.TaskList;
import sparkles.util.Parser;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represents an AddCommand.
 */
public class AddCommand extends Command {

    public AddCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command
     * Add the task to the TaskList and
     * local disk file.
     *
     * @param taskList, TaskList object containing list
     *                  of tasks.
     * @param ui,       Ui Object that interacts with user.
     * @param storage,  storage object dealing with
     *                  local disk file
     * @throws SparklesException, custom exception that handles
     * exception of Sparkles
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        Command c = Parser.parseAddCommand(command);
        c.execute(taskList, ui, storage);
    }
}
