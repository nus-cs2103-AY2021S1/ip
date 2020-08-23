public abstract class Command {
    String typeOfCommand;

    /**
     * Gets the specific command.
     *
     * @return The specific command in String.
     */
    public String getCommandType() {
        return typeOfCommand;
    }

    /**
     * Runs the specific command.
     *
     * @param taskList Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     */
    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if the program has to exit Duke.
     *
     * @return boolean True only if command is a ByeCommand.
     */
    public abstract boolean exitCheck();
}