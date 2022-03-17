package duke.command;

import duke.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

import static duke.ui.Message.concatLines;
import static duke.ui.Message.MESSAGE_NO_TASK;
import static duke.ui.Message.MESSAGE_FIND;

/**
 * Finds and lists all tasks in the task list whose description contains the specified keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a <code>FindCommand</code> Object given the specified keyword.
     *
     * @param keyword The keyword to be found in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList resultWithKeyword = taskList.filter(keyword);
        if (resultWithKeyword.isEmpty()) {
            return MESSAGE_NO_TASK;
        } else {
            return concatLines(MESSAGE_FIND, resultWithKeyword.toString());
        }
    }
}
