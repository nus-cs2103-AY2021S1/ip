package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

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
     * Execute the command to find matching tasks.
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings displayed on the UI showing sublist of tasks containing the specified keyword
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        Task[] sublist = tasks.getSublistContainingKeyword(keyword);
        return ui.getTasksWithKeywordStrings(sublist);
    }
}
