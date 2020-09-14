package src.main.java.duke.commands;

import src.main.java.duke.commons.Messages;
import src.main.java.duke.data.task.Task;

/**
 * Represents a command that updates the description of the task.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    // Message to add
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update task description in tasklist. "
            + "\nParameters: index description \n"
            + "Example: \n"
            + " update 1 read my homework";

    public static final String MESSAGE_SUCCESS = "Task updated: %1$s";

    private final String newDescription;

    /**
     * Constructor for updatedescriptionCommand
     * @param newDescription new description to be updated.
     * @param index index of the task to be updated.
     */
    public UpdateCommand(String newDescription, int index) {
        super(index);
        this.newDescription = newDescription;
    }

    @Override
    public CommandResult execute() {
        try {
            Task updatedTask = duke.updateDescription(newDescription, getTargetIndex());
            return new CommandResult(String.format(MESSAGE_SUCCESS, updatedTask));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }
}
