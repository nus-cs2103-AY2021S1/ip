package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the chat bot.
 */
public class ByeCommand extends Command {

    /**
     * Class constructor.
     */
    public ByeCommand() {
        super("bye", true);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printGoodbye();
    }

}
