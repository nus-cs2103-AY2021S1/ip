package duke.commands;

import duke.MessageManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input the user input
     */
    public ListCommand(String input) {
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
        String message = MessageManager.getListMessage(taskManager);
        ui.sendMessage(message);
    }
}
