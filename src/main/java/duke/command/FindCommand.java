package duke.command;

        import duke.storage.Storage;
        import duke.task.TaskList;
        import duke.ui.Ui;

public class FindCommand implements Command {
    String keyword;

    public FindCommand(String keyword) {
        assert !keyword.isEmpty() : "Find keyword should not be empty";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(keyword);
        if(tasks.isEmpty()) {
            return ui.format("There are no items in the list right now.");
        } else {
            return ui.format(tasks.filter(keyword).itemize("Here are the matching tasks in your list:"));
        }
    }
}
