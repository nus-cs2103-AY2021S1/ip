package fei.command;

import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Build a FindCommand with the keyword used for searching.
     *
     * @param keyword the keyword used for searching.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * To execute the find command, simply call the showMatching function in UI.
     *
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @return showMatching result.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showMatching(tasks, this.keyword);
    }

}
