package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskException;
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

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        return UI.getExitMessage();
    }
}
