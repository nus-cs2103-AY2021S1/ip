/**
 * Represents a find command which allows users to search for tasks with description 
 * that matches the search word.
 */
public class FindCommand extends Command {
    private String toMatch;

    /**
     * Creates a FindCommand object.
     * 
     * @param toMatch the word used in the search for tasks with matching descriptions 
     */
    public FindCommand(String toMatch) {
        this.toMatch = toMatch;
    }

    /**
     * Executes the finding of tasks. The user will be notified of the matching tasks 
     * through the printed messages by the ui.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used 
     * @param storage the current Storage object being used
     * @throws PandaBotException If any errors occurs when executing the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // find
        TaskList matchingTasks = tasks.FindTask(toMatch);
        
        // print
        ui.printOnFind(matchingTasks);
    }
}
