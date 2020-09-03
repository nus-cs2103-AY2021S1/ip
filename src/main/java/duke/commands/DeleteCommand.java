package duke.commands;

import static duke.utils.Messages.MESSAGE_DELETE_TASK;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Represents the command that deletes a task when executed.
 */
public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * Constructor.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task as specified by the constructor and returns a relevant message to the user.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     * @throws NoSuchTaskException If the taskDeleted is out of bounds.
     */
    @Override
    public CommandResult execute(TaskList taskList) throws NoSuchTaskException {
        Task taskDeleted = taskList.deleteTask(taskIndex);
        String response = String.format("%s\t\t%s\n\t %s", MESSAGE_DELETE_TASK,
                taskDeleted.toString(), taskList.tasksRemaining());
        return new CommandResult(response, false);
    }
}
