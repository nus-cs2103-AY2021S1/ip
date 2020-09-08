package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand lists all the tasks
 */
public class ListCommand extends Command {
    public ListCommand(String commandString) {
        super(CommandType.LIST, commandString);
    }

    /**
     * Lists all the tasks in the task list
     *
     * @param tasks task list of tasks
     */
    @Override
    public void execute(TaskList tasks) {
        Ui.showList(tasks);
    }
}
