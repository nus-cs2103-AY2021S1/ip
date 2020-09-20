package command;

import exceptions.DukeException;
import task.TaskList;

public class DeleteCommand extends Command {
    protected int taskNo;

    /**
     * Creates DeleteCommand object to execute deletion of given task number.
     * @param tasks TaskLists with Tasks to process through.
     * @param taskNo int to delete task number in tasks.
     */
    public DeleteCommand(TaskList tasks, int taskNo) {
        super(tasks);
        this.taskNo = taskNo;
    }

    /**
     * Deletes task to list of tasks.
     * @return String to inform user task has been deleted.
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        return this.tasks.deleteTask(taskNo);
    }
}
