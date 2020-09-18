public class FindCommand extends Command {
    String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Executes the find command.
     * @param tasks list of tasks.
     * @param ui user interface to display find message.
     * @param storage file storage.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFindMessage(tasks.listFilteredTasks(this.filter));
    }
}
