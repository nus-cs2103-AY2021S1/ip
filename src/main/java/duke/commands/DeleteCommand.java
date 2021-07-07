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

    /**
     * Constructor to create a new {@code DeleteCommand} object.
     *
     * @param taskIndex the index of the task which will be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task based on the task index and returns the appropriate output after deleting the task.
     *
     * @param taskManager the {@code TaskManager} object which holds the list of all the {@code Task} instances.
     * @return the output after deleting the task.
     * @throws DukeException if the index is out of the bounds of the list of tasks stored in {@code TaskManager}
     *      * object.
     */
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
