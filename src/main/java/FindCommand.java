public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayList(tasks.find(query), true);
    }
}
