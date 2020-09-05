package focus.command;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the FindCommand to find tasks based on user's input. */
public class FindCommand extends Command {
    /**
     * Returns false since FindCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes FindCommand to find tasks based on user's input.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of found tasks.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        String keyword;
        try {
            keyword = input.split("find")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FocusException("\tPlease enter a keyword you wish to find!");
        }
        if (keyword.isBlank()) {
            throw new FocusException("\tPlease enter a keyword you wish to find!");
        }
        assert !keyword.isEmpty() : "Keyword should not be blank here.";
        return taskList.findTasks(keyword.trim());
    }
}
