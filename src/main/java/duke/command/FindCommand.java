package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to search for keywords.
 */
public class FindCommand extends Command {

    private static String keyword;

    /**
     * Constructor for a new FindCommand object.
     *
     * @param keyword keyword that user is searching for.
     */
    public FindCommand(String keyword) {
        duke.command.FindCommand.keyword = keyword;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui object to output messages.
     * @param storage object to write TaskList to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            TaskList matching = new TaskList (tasks.findTask(keyword));
            if (!matching.isEmpty()) {
                Ui.displayMatchingTasks(matching);
            } else {
                Ui.display("No matching tasks!");
            }
        } catch (Exception e) {
            throw new DukeException("Please include a keyword to search for!");
        }
    }
}

