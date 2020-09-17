package duke.commands;

import duke.data.task.Task;
import duke.utils.Messages;

/**
 * Deletes a task identified using it's last displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\nDeletes the task identified by the index number used.\n"
            + "  Parameters: INDEX\n"
            + "  Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Got it. I've deleted this task:\n"
            + "\t%1$s\n"
            + "Now you have %2$d tasks in the list.";

    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            taskList.remove(target);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, target, taskList.size()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

}
