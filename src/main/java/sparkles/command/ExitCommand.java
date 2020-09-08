package sparkles.command;

import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represent an exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Perform exit on the system.
     *
     * @param taskList TaskList object containing list
     *                  of tasks
     * @param ui       Ui Object that interacts with user
     * @param storage  storage object dealing with
     *                  local disk file
     * @return response to the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return this.getResponse(ui, null, null);
    }

    /**
     * Only command that returns true for this method.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getResponse(Ui ui, Task task, TaskList taskList) {
        assert task == null;
        assert taskList == null;

        ui.print("     Bye. Hope to see you again!");
        ui.showLine();
        System.exit(0);
        return "Bye. Hope to see you again!";
    }
}
