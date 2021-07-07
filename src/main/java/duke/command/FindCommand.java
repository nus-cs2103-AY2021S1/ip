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
     * @param taskList Task list
     * @param archive
     * @param ui Ui
     * @return Output strings displayed on the UI showing sublist of tasks containing the specified keyword
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert taskList != null;
        assert ui != null;

        Task[] sublist = taskList.getSublistContainingKeyword(keyword);
        return ui.getTasksWithKeywordStrings(sublist);
    }
}
