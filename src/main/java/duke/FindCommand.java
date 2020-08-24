package duke;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        TaskList keywordTasks = list.findTask(keyword);
        String output = "Here are your tasks with keyword(s) \"" + this.keyword + "\" :\n";
        output += keywordTasks;
        ui.printLine(output);
    }

}
