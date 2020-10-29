package pandabot.commands;

import pandabot.exceptions.PandaBotException;
import pandabot.storage.Storage;
import pandabot.tasks.Task;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a delete command which allows users to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskNum the task number to be deleted
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the deletion of tasks. The user will be notified through the
     * printed messages by the ui and the tasks remaining are saved.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     * @throws PandaBotException If any errors occurs when executing the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // delete task
        Task t = tasks.getTaskAt(taskNum);
        tasks.deleteTask(taskNum);

        // save
        storage.write(tasks.getTaskList());
        return ui.displayOnDelete(t, tasks.size());
    }
}
