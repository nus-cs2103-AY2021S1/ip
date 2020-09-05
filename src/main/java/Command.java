import java.io.IOException;

/**
 * Manages the user command and modify the taskList, output, and storage accordingly.
 */

public abstract class Command {
    TaskList taskList;
    Ui ui;
    Storage storage;
    boolean isExit = false;

    /**
     * Adds a Task to the TaskList and update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        storage.updateFile(taskList);
    }

    /**
     * Boolean indicator for Command to end the program.
     * @return boolean indicator.
     */
    public boolean isExit() {
        return isExit;
    }

}
