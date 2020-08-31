package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String out = ui.showList() + "\n";
        int count = 1;
        for (Task task : list.getList()) {
            out = out + ui.showTask(count, task) + "\n";
            count++;
        }
        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
