package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Represents an owo command.
 */
public class OwoCommand extends Command {
    public static final String COMMAND_WORD = "owo";
    private static final String MESSAGE_OWO_ACKNOWLEDGEMENT = "uwu";

    /**
     * Displays acknowledgement message to owo command.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_OWO_ACKNOWLEDGEMENT);
    }

}
