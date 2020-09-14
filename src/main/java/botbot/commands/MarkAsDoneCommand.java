package botbot.commands;

import botbot.CommandValidator;
import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;
import botbot.tasks.Task;
import botbot.tasks.TaskStatus;

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
            task.markAsDone();
            assert task.getStatus().equals(TaskStatus.DONE.getStrValue()) : "Mark task as done unsuccessful";
            storage.save(tasks);
            return ui.showMarkAsDoneResponse(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorResponse(CommandValidator.ERROR_MESSAGE_INVALID_TASK_ID);
        }
    }
}
