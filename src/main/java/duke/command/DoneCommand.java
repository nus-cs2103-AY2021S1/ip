package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the DoneCommand to mark tasks done on task list.
 */
public class DoneCommand extends Command {
    /**
     * Returns false since DoneCommand is not an ExitCommand.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes DoneCommand to mark tasks done.
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @throws DukeException If input does not meet criteria.
     * @return
     */
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number you wish to mark done!\n"
                    + "You have " + taskList.getSize() + " tasks on your list now.");
        }
        if (indexString.isBlank()) {
            throw new DukeException("Please enter a task number you wish to mark done!\n"
                    + "You have " + taskList.getSize() + " tasks on your list now.");
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.getSize())) {
            throw new DukeException("There is no such task number.\n"
                    + "Please enter a valid one!");
        }
        return taskList.markTaskDone(index, storage);
    }
}
