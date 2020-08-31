package alison.command;

import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Build a FindCommand with the keyword used for searching.
     * @param keyword the keyword used for searching.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * To execute the find command, simply call the showMatching function in UI.
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatching(tasks, this.keyword);
    }

}
