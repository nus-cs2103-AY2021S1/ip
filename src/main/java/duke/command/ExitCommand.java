package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Command that signals exiting the bot and broadcasts the appropriate update.
 */
public class ExitCommand extends Command {

    /** Default message to be sent when a Task is added */
    protected static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Returns the default implementation of an ExitCommand to signal Exiting the bot.
     *
     * @return exit command.
     */
    public static ExitCommand create() {
        return new ExitCommand();
    }


    /**
     * Signals exit to the bot.
     * The command does not perform any manipulation.
     * The method also broadcasts an update through the UI and updates the storage file.
     *
     * @param taskList List of Tasks to work with.
     * @param ui UI element to be used.
     * @param storage Storage element to be used.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.outputMessage(getClosingText());
    }

    /** Method used to create the closing message. For now it just returns the pre-determined message. */
    private String getClosingText() {
        return CLOSING_MESSAGE;
    }

    /**
     * Returns true to signal to the bot that it is time to exit.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }


}
