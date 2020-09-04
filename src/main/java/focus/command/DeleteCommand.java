package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidTaskNumberException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the DeleteCommand to delete tasks from task list. */
public class DeleteCommand extends Command {
    /**
     * Returns false since DeleteCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes DeleteCommand to delete tasks.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of deleted task.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        String indexString;
        try {
            indexString = input.substring(7);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        if (indexString.isBlank()) {
            throw new InvalidTaskNumberException();
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.getSize())) {
            throw new InvalidTaskNumberException();
        }
        return taskList.deleteTask(index, storage);
    }
}
