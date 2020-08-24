package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to find all tasks containing a keyword.
 */
public class FindCommand extends Command {
    /** The keyword to find tasks with. */
    private String keyword;

    /**
     * Creates a new find command with the specified keyword.
     * @param keyword The keyword to find tasks with.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all the tasks containing the keyword in the specified task list.
     * @param tasks The tasks the command is executed with.
     * @param ui The ui the command is executed with.
     * @param storage The storage the command is executed with.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printFindTasks(tasks, keyword);
    }
}
