package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidTaskNumberException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the DoneCommand to mark tasks done on task list. */
public class DoneCommand extends Command {
    /**
     * Returns false since DoneCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes DoneCommand to mark tasks done.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of done task.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        String indexString;
        try {
            indexString = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        if (indexString.isBlank()) {
            throw new InvalidTaskNumberException();
        }
        assert !indexString.isEmpty() : "Index string should not be blank here.";

        int index = Integer.parseInt(indexString);
        boolean indexIsLessThanZero = index <= 0;
        boolean indexIsMoreThanListSize = index > taskList.getSize();
        if (indexIsLessThanZero || indexIsMoreThanListSize) {
            throw new InvalidTaskNumberException();
        }
        assert !(indexIsLessThanZero || indexIsMoreThanListSize) : "Index should not be less than 0 or exceed"
                + " task list size.";
        return taskList.markTaskDone(index, storage);
    }
}
