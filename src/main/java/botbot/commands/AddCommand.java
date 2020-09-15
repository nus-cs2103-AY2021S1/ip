package botbot.commands;

import botbot.utils.Storage;
import botbot.utils.TaskList;
import botbot.ui.Ui;
import botbot.tasks.Task;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates an add command to add the specified task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to add task to.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(task);
        assert tasks.size() > 0 : "Empty task list after adding task";
        storage.save(tasks);
        return ui.showAddResponse(task, tasks.size());
    }
}
