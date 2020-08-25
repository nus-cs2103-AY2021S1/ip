package command;

import exceptions.DukeException;
import task.TaskList;

public class doneCommand extends Command {
    int taskNo;

    public doneCommand(TaskList tasks, int taskNo) {
        super(tasks);
        this.taskNo = taskNo;
    }

    /**
     * Marks task as done in list of tasks.
     *
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        this.tasks.markTaskDone(taskNo);
    }
}
