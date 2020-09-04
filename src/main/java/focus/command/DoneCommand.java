package focus.command;

import focus.exception.FocusException;
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
            throw new FocusException("\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.");
        }
        if (indexString.isBlank()) {
            throw new FocusException("\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.");
        }
        assert !indexString.isEmpty() : "Index string should not be blank here.";

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.getSize())) {
            throw new FocusException("\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!");
        }
        assert !((index <= 0) || (index > taskList.getSize())) : "Index should not be less than 0 or exceed"
                + " task list size.";
        return taskList.markTaskDone(index, storage);
    }
}
