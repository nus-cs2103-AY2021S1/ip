package duke.Command;

import duke.Storage;
import duke.Task.TaskList;
import duke.Ui.Message;
import duke.Ui.Ui;

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