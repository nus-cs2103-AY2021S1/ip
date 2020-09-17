package duke.commands;

import duke.data.task.Task;
import duke.utils.Messages;

/**
 * Marks a task identified done using it's last displayed index from the task list.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\nMarks the task identified by the index number used as done.\n"
            + "  Parameters: INDEX\n"
            + "  Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Nice! I've marked this task as done:\n\t%1$s";

    public DoneCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            target.markAsDone();
            return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, target));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

}
