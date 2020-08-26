package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represent an unclear command.
 */
public class TaskUnclearCommand extends Command {

    public TaskUnclearCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Executed when user input an unknow command.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws SparklesException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("     Task need to be more specific!");
    }

}
