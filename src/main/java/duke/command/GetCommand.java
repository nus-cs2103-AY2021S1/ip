package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.Date;
import java.util.List;

/**
 * Represents a command where user queries tasks using a dates.
 */
public class GetCommand extends Command {

    Date date;

    /**
     * Instantiates a GetCommand with the queried date.
     * @param date the queried date
     */
    public GetCommand(Date date) {
        this.date = date;
    }

    /**
     * Returns false since this is not an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by querying tasks from taskList and displaying
     * the matching tasks.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage storing on disk
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasksWithDate(date);
        ui.listQueriedTasks(tasks);
    }
}
