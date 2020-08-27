package alison.command;

import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatching(tasks, this.keyword);
    }
}
