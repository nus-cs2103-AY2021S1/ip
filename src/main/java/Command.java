/**
 * Represents a Command, which is an abstract class.
 * The Command class is used to execute a command.
 */
public abstract class Command {

    /**
     * Executes the command. The user will be notified through the 
     * printed messages by the ui and the current tasks are saved.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used 
     * @param storage the current Storage object being used
     * @throws PandaBotException If any errors occurs when executing the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException;

    /**
     * Returns true if the program should exit. 
     * Otherwise, returns false if the program should continue to run.
     * 
     * @return false by default as the program is still running
     */
    public boolean isExit() {
        return false;
    };
}
