package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the FindCommand to find tasks based on user's input.
 */
public class FindCommand extends Command {
    /**
     * Returns false since FindCommand is not an ExitCommand.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes FindCommand to find tasks based on user's input.
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @throws DukeException If input does not meet criteria.
     */
    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String keyword;
        try {
            keyword = input.split("find")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\tPlease enter a keyword you wish to find!");
        }
        if (keyword.isBlank()) {
            throw new DukeException("\tPlease enter a keyword you wish to find!");
        }
        taskList.findTasks(keyword.trim());
    }
}
