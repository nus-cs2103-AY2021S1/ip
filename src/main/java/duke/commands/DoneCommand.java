package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the command which will mark a particular task as completed upon execution.
 */

public class DoneCommand extends Command {
    private int completedTaskIndex;

    public DoneCommand(int completedTaskIndex) {
        this.completedTaskIndex = completedTaskIndex;
    }

    @Override
    public CommandOutput executeCommand(TaskManager taskManger, Storage storage) throws DukeException {
        try {
            Task completedTask = taskManger.getTask(completedTaskIndex - 1);
            taskManger.markTaskAsDone(completedTaskIndex);
            storage.save(taskManger);
            String taskDoneMessage = Messages.TASK_MARKED_AS_DONE_MESSAGE;
            StringBuilder stringBuilder = new StringBuilder(taskDoneMessage);
            stringBuilder.append(completedTask.toString());
            return new CommandOutput(stringBuilder.toString(), false);
        } catch (StorageOperationException e) {
            throw new DukeException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }
}
