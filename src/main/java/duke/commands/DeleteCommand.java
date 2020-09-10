package duke.commands;

import duke.exceptions.DukeException;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the command which will delete a particular task when it is executed.
 */

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public CommandOutput executeCommand(TaskManager taskManager) throws DukeException {
        try {
            Task deletedTask = taskManager.getTask(taskIndex);
            taskManager.deleteTask(taskIndex);
            String deletedTaskOutput = outputResult(deletedTask);
            return new CommandOutput(deletedTaskOutput, false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }

    private String outputResult(Task deletedTask) {
        StringBuilder deletedTaskOutput = new StringBuilder("Noted. I have removed the task:\n");
        String deletedTaskDescription = deletedTask.toString();
        deletedTaskOutput.append(deletedTaskDescription);
        return deletedTaskOutput.toString();
    }
}
