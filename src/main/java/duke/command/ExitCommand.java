package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A command to exit the bot. */
public class ExitCommand extends Command {

    /** Constructs an ExitCommand. */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Executes the command by printing a goodbye message.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param ui       The UI of the bot.
     * @param storage  The storage system of the bot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }
}
