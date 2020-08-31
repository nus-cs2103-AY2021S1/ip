package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

public class FindCommand extends Command {
    private final String target;

    public FindCommand(String detail) {
        this.target = detail.trim();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String out = "";
        boolean hasFound = false;
        int count = 1;
        for (Task task : list.getList()) {
            if (task.getName().contains(this.target)) {
                if (!hasFound) {
                    out = out + ui.showFind() + "\n";
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
