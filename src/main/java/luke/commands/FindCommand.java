package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents a command to find matching tasks.
 */
public class FindCommand extends Command {

    protected String keyword;

    /**
     * Creates a FindCommand object to find tasks
     * that match the given keyword.
     *
     * @param keyword the keyword used to find matching tasks
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showFindResult(tasks, keyword);
    }
}
