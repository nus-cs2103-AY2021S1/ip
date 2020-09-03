package duke.commands;

import static duke.utils.Messages.MESSAGE_ADD_TASK;

import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Represents the command that adds a task to the taskList when executed.
 */
public class AddCommand extends Command {

    /** The task to be added. */
    private Task toAdd;

    /**
     * Constructor.
     *
     * @param toAdd The task to be added to the taskList.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Adds the task specified in the constructor to the taskList and returns a relevant message.
     *
     * @param taskList The taskList that the task is being added to.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.addTask(toAdd);
        String response = String.format("%s\t\t%s\n\t %s", MESSAGE_ADD_TASK, toAdd.toString(),
                taskList.tasksRemaining());
        return new CommandResult(response, false);
    }

}
