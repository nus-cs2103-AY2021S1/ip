/**
 * Implements methods for ByeCommand.
 */
public class ByeCommand extends Command {
    protected boolean isExit;

    /**
     * Runs command to exit Duke.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     */
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        Response responseObject = ui.printByeMessage();
        if (storage.isStorageChanged()) {
            try {
                storage.saveToDisk(arrayOfTasks);
                responseObject = ui.finishWriting();
            } catch (DukeException error) {
                return ui.writeError();
            }
        }
        return responseObject;
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return boolean true to signal exit from Duke program.
     */
    public boolean exitChecker() {
        isExit = true;
        return isExit;
    }
}
