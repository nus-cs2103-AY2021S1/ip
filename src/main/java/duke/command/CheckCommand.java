package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

public class CheckCommand extends Command {
    private final String target;

    public CheckCommand(String detail) {
        this.target = detail;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String out = "";
        boolean hasFound = false;
        int count = 1;
        for (Task task : list.getList()) {
            if (task.getDate().equals(this.target)) {
                if (!hasFound) {
                    out = out + ui.showCheck() + "\n";
                    hasFound = true;
                }
                out = out + ui.showTask(count, task) + "\n";
                count++;
            }
        }

        if(!hasFound) {
            out = ui.showNothingFound();
        }

        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
