package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to find tasks which matches a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new find command with the intended keyword.
     * @param keyword is the keyword used to find the tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks which matches the keyword.
     * @param tasks is the tasks the command will execute with.
     * @param ui is the ui the command will execute with.
     * @param storage is the storage the command will execute with.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFindTasks(keyword, tasks);
    }
}