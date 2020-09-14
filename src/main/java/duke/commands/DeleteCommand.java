package src.main.java.duke.commands;

import src.main.java.duke.commons.Messages;
import src.main.java.duke.data.task.Task;

/**
 * Represents a command that deletes the task in the list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the task list.\n"
            + "Parameters: INDEX\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        try {
            final int target = getTargetIndex();
            Task task = duke.removeTask(target);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, task));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }

}
