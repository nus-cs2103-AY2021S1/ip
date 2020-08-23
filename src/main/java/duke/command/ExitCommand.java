package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the ExitCommand to exit Focus.
 */
public class ExitCommand extends Command {
    /**
     * Returns true since ExitCommand is not an ExitCommand.
     * @return True.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes ExitCommand to exit Focus.
     * @param input User's input (not used here).
     * @param taskList Task list created for user (not used here).
     * @param storage Storage created for user (not used here).
     */
    public void execute(String input, TaskList taskList, Storage storage) {
        return;
    }
}
