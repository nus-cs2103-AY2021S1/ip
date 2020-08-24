package commands;

import data.task.Task;
import utils.Messages;

// Marks a data.task identified done using it's last displayed index from the data.task list.
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Marks the data.task identified by the index number used as done.\n" +
            "\tParameters: INDEX\n" +
            "\tExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Nice! I've marked this data.task as done:\n\t%1$s";

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
