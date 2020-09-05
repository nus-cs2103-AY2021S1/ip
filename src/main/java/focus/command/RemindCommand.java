package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidRemindCommandException;
import focus.exception.NoTasksException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the RemindCommand to remind user of the tasks
 * (within a specified time frame) on task list. */
public class RemindCommand extends Command {
    /**
     * Returns false since RemindCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes RemindCommand to remind user of tasks.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of list of tasks.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        String checker;
        if (taskList.getSize() == 0) {
            throw new NoTasksException();
        }
        assert !(taskList.getSize() < 0) : "Task list size should not be less than zero at all.";
        if (input.length() == 6) {
            return taskList.remindUserOfTasksWithin(storage.getNumberOfDays());
        }
        assert !(input.length() <= 6) : "Input length should be more than 6 here.";
        try {
            checker = input.split("remind")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidRemindCommandException();
        }
        if (!checker.isBlank()) { // user's input is "remind me" for example
            throw new InvalidRemindCommandException();
        } else { // user's input is "remind " with spacings
            assert !checker.isEmpty() : "Checker should not be blank here.";
            return taskList.remindUserOfTasksWithin(storage.getNumberOfDays());
        }
    }
}
