package duke;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        super();
        this.query = query;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        if (query.isBlank()) {
            return "Please enter a valid query!";
        } else {
            return "Here are the matching tasks in your list:\n"
                    + tasks.matchedTasksOnly(query);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
