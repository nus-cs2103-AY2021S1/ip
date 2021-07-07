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

    /**
     * Constructor to create a new {@code DoneCommand} object.
     *
     * @param completedTaskIndex the index of that task that is to be marked as completed.
     */
    public DoneCommand(int completedTaskIndex) {
        this.completedTaskIndex = completedTaskIndex;
    }

    /**
     * Mark the task as done and generate the output after executing the command.
     *
     * @param taskManger the {@code TaskManager} object which holds the list of all the {@code Task} instances.
     * @return the output after marking a task as done.
     * @throws DukeException if the index is out of the bounds of the list of tasks stored in {@code TaskManager}
     * object.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) throws DukeException {
        try {
            taskManger.markTaskAsDone(completedTaskIndex);
            Task completedTask = taskManger.getTask(completedTaskIndex);
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
