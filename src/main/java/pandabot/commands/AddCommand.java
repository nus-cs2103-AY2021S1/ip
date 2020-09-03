package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.tasks.Task;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents an add command which allows users to add tasks to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates an AddCommand object.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of tasks. The user will be notified through the
     * printed messages by the ui and the current tasks are saved.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // add the task
        tasks.addTask(task);
        // save
        storage.write(tasks.getTaskList());
        return ui.displayOnAddTask(task, tasks.size());
    }
}
