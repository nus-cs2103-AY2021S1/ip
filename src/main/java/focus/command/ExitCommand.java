package focus.command;

import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the ExitCommand to exit Focus. */
public class ExitCommand extends Command {
    /**
     * Returns true since ExitCommand is an ExitCommand.
     *
     * @return True.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes ExitCommand to exit Focus.
     *
     * @param input User's input (not used here).
     * @param taskList Task list created for user (not used here).
     * @param storage Storage created for user (not used here).
     * @return Empty string.
     */
    public String execute(String input, TaskList taskList, Storage storage) {
        assert false : "Program should not reach here.";
        return "";
    }
}
