public class ExitCommand extends Command {
    
    /**
     * Saves all the tasks in the taskList to the hard disk
     * and displays the UI for goodbye.
     * @param tasks the current TaskList.
     * @param ui the current Ui.
     * @param storage the current Storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return ui.showBye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
