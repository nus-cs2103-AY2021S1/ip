package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command to find tasks that match a given keyword
 */
public class FindCommand extends Command {

    /** Keyword to find */
    final private String keyword;

    /**
     * Constructor
     * @param keyword Keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the command to find matching tasks
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.getTasksWithKeywordStrings(tasks.getSublistContainingKeyword(this.keyword));
    }
}
