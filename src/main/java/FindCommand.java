public class FindCommand extends Command {
    String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFindMessage(tasks.listFilteredTasks(this.filter));
    }
}
