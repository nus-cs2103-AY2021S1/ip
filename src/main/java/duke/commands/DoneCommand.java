package duke.commands;

import duke.exceptions.DukeException;

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
    public CommandOutput executeCommand(TaskManager taskManger) throws DukeException {
        try {
            taskManger.markTaskAsDone(completedTaskIndex);
            Task completedTask = taskManger.getTask(completedTaskIndex - 1);
            String doneTaskOutput = outputResult(completedTask);
            return new CommandOutput(doneTaskOutput, false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }

    private String outputResult(Task completedTask) {
        StringBuilder doneTaskOutput = new StringBuilder(Messages.TASK_MARKED_AS_DONE_MESSAGE);
        doneTaskOutput.append(completedTask.toString());
        return doneTaskOutput.toString();
    }
}
