package main.java.seedu.duke.commands;

import main.java.seedu.duke.DukeException;
import main.java.seedu.duke.Storage;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;

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
