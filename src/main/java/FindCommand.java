public class FindCommand extends Command {
    String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindMessage();
        tasks.listFilteredTasks(this.filter);
    }
}
