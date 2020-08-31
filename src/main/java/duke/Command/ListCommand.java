package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Lists all the tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            return Message.MESSAGE_NO_TASK;
        } else {
            return Message.concatLines(Message.MESSAGE_LIST,
                    Ui.LINE_SEPARATOR, taskList.toString());
        }
    }
}