package duke.command;

import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

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
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        return ui.displayMatchingTasks(manager.findTasks(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

