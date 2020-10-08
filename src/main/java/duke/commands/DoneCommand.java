package duke.commands;

import static duke.utils.Messages.MESSAGE_DONE_TASK;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Represents the command that marks a task as done when executed.
 */
public class DoneCommand extends Command {

    private int taskIndex;

    /**
     * Constructor.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task, as specified by the taskIndex in the constructor, as done and
     * returns a relevant message to the user.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     * @throws NoSuchTaskException If the taskIndex is out of bounds.
     */
    @Override
    public CommandResult execute(TaskList taskList) throws NoSuchTaskException {
        Task taskDone = taskList.markTaskAsDone(taskIndex);
        String response = String.format("%s\t\t%s", MESSAGE_DONE_TASK, taskDone.toString());
        return new CommandResult(response, false);
    }
}
