package duke.commands;

import duke.MessageManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input the user input
     */
    public ExitCommand(String input) {
        this.input = input;
        this.isExit = true;
    }

    /**
     * Execution instructions for the command.
     *
     * @param taskManager the taskManager
     * @param ui          the ui to return output to
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.sendMessage(MessageManager.getByeMessage());
    }
}
