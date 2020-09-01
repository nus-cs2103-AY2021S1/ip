package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(
                tasks.findTasks(toFind));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
