package command;

import exceptions.DukeException;
import task.TaskList;

public class deleteCommand extends Command {
    int taskNo;

    public deleteCommand(TaskList tasks, int taskNo) {
        super(tasks);
        this.taskNo = taskNo;
    }

    /**
     * Deletes task to list of tasks.
     *
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        this.tasks.deleteTask(taskNo);
    }
}
