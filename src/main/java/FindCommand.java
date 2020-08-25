public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayList(tasks.find(query), true);
    }
}
