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
    public void execute(TaskList list, Ui ui, Storage storage) {
        boolean hasFound = false;
        int count = 1;
        for (Task task : list.getList()) {
            if (task.getDate().equals(this.target)) {
                if (!hasFound) {
                    ui.showCheck();
                    hasFound = true;
                }
                ui.showTask(count, task);
                count++;
            }
        }

        if(!hasFound) {
            ui.showNothingFound();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
