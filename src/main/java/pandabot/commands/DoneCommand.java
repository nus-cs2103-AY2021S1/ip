package pandabot.commands;

import pandabot.exceptions.PandaBotException;
import pandabot.storage.Storage;
import pandabot.tasks.Task;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents the done command which allows users to mark a task as done.
 */
public class DoneCommand extends Command {
    private final int taskNum;

    /**
     * Creates a DoneCommand object.
     *
     * @param taskNum the task number that is done
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes marking the task as done. The user will be notified through the
     * printed messages by the ui and the tasks are saved.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     * @throws PandaBotException If any errors occurs when executing the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // mark as done
        Task t = tasks.getTaskAt(taskNum);
        t.markTaskDone();

        // save the changes
        storage.write(tasks.getTaskList());
        return ui.displayOnDone(tasks.getTaskAt(taskNum));
    }

}
