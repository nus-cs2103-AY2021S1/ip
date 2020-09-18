/**
 * Abstract class for all command classes.
 */
public abstract class Command {
    protected String typeOfCommand;

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
     * @return Response message object
     * @throws DukeException if there is an issue
     */
    public abstract Response runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the program has to exit Duke.
     *
     * @return boolean True only if command is a ByeCommand.
     */
    public abstract boolean exitChecker();
}
