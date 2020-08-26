package duke.commands;

import duke.TaskManager;
import duke.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input the user input
     */
    public InvalidCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     *
     * @param taskManager the taskManager
     * @param ui          the ui to return output to
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.sendMessage("I don't know what you just sent...");
    }
}
