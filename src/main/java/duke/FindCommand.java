package duke;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        super();
        this.query = query;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        if (query.isBlank()) {
            ui.display("Please enter a valid query!");
        } else {
            ui.display("Here are the matching tasks in your list:\n"
                    + tasks.matchedTasksOnly(query));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
