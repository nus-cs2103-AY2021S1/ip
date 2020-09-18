/**
 * Encapsulates an exit command.
 */
public class ExitCommand extends Command {
    
    /**
     * Saves all the tasks in the task list into the 
     * storage and displays the ui for goodbye message.
     * @param tasks the current task list.
     * @param ui the ui used to display the goodbye message.
     * @param storage the storage used to store the task list.
     * @return a string representation of the goodbye message.
     * @throws DukeException throws an exception when saving
     * the task list into the storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        return ui.showBye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
