package command;

import exceptions.DukeException;
import task.TaskList;

public class DoneCommand extends Command {
    protected int taskNo;

    /**
     * Creates DoneCommand object to execute marking given task as completed.
     * @param tasks TaskLists with Tasks to process through.
     * @param taskNo int to mark task number in tasks as done.
     */
    public DoneCommand(TaskList tasks, int taskNo) {
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
