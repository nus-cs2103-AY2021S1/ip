package duke.command;

import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to find all matching tasks with a specified keyword.
 */
public class FindCommand extends Command {

    /**
     * The keyword(s) used to find matching tasks.
     */
    private final String[] keywords;

    /**
     * Constructs a command that finds all matching tasks.
     *
     * @param keywords The keyword(s) used to find matching tasks.
     */
    public FindCommand(String... keywords) {
        assert keywords.length > 0 : "There should be at least 1 keyword to find.";
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        return ui.displayMatchingTasks(manager.findTasks(keywords));
    }
}

