package duke.command;

import duke.Storage;

import duke.task.TaskList;

import duke.ui.Message;
import duke.ui.Ui;

/**
 * Finds and lists all tasks in the task list whose description contains the specified keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList resultWithKeyword = taskList.filter(keyword);
        if (resultWithKeyword.isEmpty()) {
            return Message.MESSAGE_NO_TASK;
        } else {
            return Message.concatLines(Message.MESSAGE_FIND,
                    Ui.LINE_SEPARATOR, resultWithKeyword.toString());
        }
    }
}
