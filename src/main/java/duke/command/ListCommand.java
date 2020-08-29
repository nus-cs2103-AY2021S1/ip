package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command which list all tasks in the task list.
 */
public class ListCommand implements Command {

    private TaskList tasks;

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        this.tasks = tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return Ui.getTaskListString(tasks);
    }

}
