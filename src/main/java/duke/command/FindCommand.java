package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        ui.displayMatchingTasks(manager.findTasks(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
