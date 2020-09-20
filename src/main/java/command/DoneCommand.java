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
     * @return String to inform user task has been marked as done.
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        return this.tasks.markTaskDone(taskNo);
    }
}
