package botbot.commands;

import botbot.tasks.Task;
import botbot.tasks.TaskList;
import botbot.ui.Ui;
import botbot.utils.Storage;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";
    private final int id;

    /**
     * Creates a delete command to delete the specified task.
     *
     * @param id ID of task to be deleted.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the delete command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to delete task from.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            tasks.delete(id);
            storage.save(tasks);
            return ui.showDeleteResponse(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorResponse(CommandValidator.ERROR_MESSAGE_INVALID_TASK_ID);
        }
    }
}
