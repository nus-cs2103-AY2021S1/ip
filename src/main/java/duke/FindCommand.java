package duke;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        TaskList keywordTasks = list.findTask(keyword);
        String output = "Here are your tasks with keyword(s) \"" + this.keyword + "\" :\n";
        output += keywordTasks;
        return ui.getLine(output);
    }

}
