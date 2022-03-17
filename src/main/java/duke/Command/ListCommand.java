package duke.command;

import duke.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

import static duke.ui.Message.concatLines;
import static duke.ui.Message.MESSAGE_LIST;
import static duke.ui.Message.MESSAGE_NO_TASK;

/**
 * Lists all the tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            return MESSAGE_NO_TASK;
        } else {
            return concatLines(MESSAGE_LIST, taskList.toString());
        }
    }
}
