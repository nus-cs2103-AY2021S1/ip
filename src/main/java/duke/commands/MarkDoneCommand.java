package src.main.java.duke.commands;

import src.main.java.duke.commons.Messages;

/**
 * Represents a command that marks the command done.
 */
public class MarkDoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark a task done in the tasklist. \n"
            + "Parameters: done INDEX\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Mark done task: %1$s";

    public MarkDoneCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        try {
            assert getTargetIndex() >= 0 : 0;
            final int target = getTargetIndex();
            return new CommandResult(String.format(MESSAGE_SUCCESS, duke.markDone(target)));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }

}
