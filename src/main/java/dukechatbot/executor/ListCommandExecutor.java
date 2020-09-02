package executor;

import command.Command;
import dukeoutput.DukeOutput;
import tasklist.TaskList;

/**
 * Represents executor of list command.
 * Executes the action of listing tasks in the task list.
 */
public class ListCommandExecutor extends CommandExecutor {

    /**
     * Lists all the tasks in the task list.
     *
     * @param command
     * @param taskList
     */
    @Override
    public void execute(Command command, TaskList taskList) {
        DukeOutput.output(taskList.getListInfo());
    }
}
