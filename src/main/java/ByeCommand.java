public class ByeCommand extends Command {
    boolean exitCheck;

    /**
     * Runs command to exit Duke.
     *
     * @param taskList Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     */
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        // Exit program.
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return boolean true to signal exit from Duke program.
     */
    public boolean exitCheck() {
        exitCheck = true;
        return exitCheck;
    }
}