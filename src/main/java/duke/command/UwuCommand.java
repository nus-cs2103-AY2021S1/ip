package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an uwu command.
 */
public class UwuCommand extends Command {
    public static final String COMMAND_WORD = "uwu";
    private static final String MESSAGE_UWU_ACKNOWLEDGEMENT = "owo";

    /**
     * Displays acknowledgement message to uwu command.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_UWU_ACKNOWLEDGEMENT);
    }

}
