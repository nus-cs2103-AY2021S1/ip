package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) throws DukeException {
        this.taskIndex = taskIndex;
    }

    /**
     * Execute the command.
     *
     * @param storage             Storage to save data to.
     * @param tasks               The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The response of Duke to the user Input.
     * @throws DukeException if the system fails to execute.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        return Ui.showDeletedTasks(deletedTask.toString());
    }
}
