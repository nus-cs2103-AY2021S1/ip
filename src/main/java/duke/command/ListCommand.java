package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            ui.showError("No tasks found");
        } else {
            ui.replyList(taskList.toString());
        }
    }
}
