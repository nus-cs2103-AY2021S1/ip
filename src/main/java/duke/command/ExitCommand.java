package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Encapsulates the logic for exiting tasks.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a ExitCommand.
     *
     * @param args Arguments for the command.
     */
    public ExitCommand(String args) {
        super(args);
    }

    /**
     * Sends a signal to exit program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Sends a signal to the main process to end the application.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return The exit message taken from the duke.ui.ui class.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return UI.getExitMessage();
    }
}
