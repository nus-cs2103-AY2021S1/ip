package botbot.commands;

import botbot.tasks.Task;
import botbot.tasks.TaskList;
import botbot.tasks.TaskStatus;
import botbot.ui.Ui;
import botbot.utils.Storage;

/**
 * Marks a task in the task list as done.
 */
public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "done";
    private final int id;

    /**
     * Creates a mark as done command to mark the specified task as done.
     *
     * @param id ID of task to be marked as done.
     */
    public MarkAsDoneCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the mark as done command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to mark task as done from.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            assert task != null : "Empty task";
            task.markAsDone();
            assert task.getStatus().equals(TaskStatus.DONE.getStrValue()) : "Mark task as done unsuccessful";
            storage.save(tasks, 0);
            return ui.showMarkAsDoneResponse(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorResponse(CommandValidator.ERROR_MESSAGE_INVALID_TASK_ID);
        }
    }
}
