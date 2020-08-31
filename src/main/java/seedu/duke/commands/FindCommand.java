package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword The keyword to be found.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.searchKeyword(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
