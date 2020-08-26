package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to find all matching tasks with a specified keyword.
 */
public class FindCommand extends Command {

    /**
     * The keyword used to find matching tasks.
     */
    private final String keyword;

    /**
     * Constructs a command that finds all matching tasks.
     *
     * @param keyword The keyword used to find matching tasks.
     */
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
