package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.tool.TaskList;

/**
 * Represents a command which list all tasks in the task list.
 */
public class ListCommand implements Command {

    private TaskList tasks;

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        this.tasks = tasks;
        assert tasks.getSize() > 0;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        assert !isExit();
        return tasks.getTaskListString();
    }

}
