package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidListCommandException;
import focus.exception.NoTasksException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the ListCommand to list all the tasks on task list. */
public class ListCommand extends Command {
    /**
     * Returns false since ListCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes ListCommand to list the tasks.
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
        if (input.length() == 4) {
            return taskList.listTasks();
        }
        assert !(input.length() <= 4) : "Input length should be more than 4 here.";
        try {
            checker = input.split("list")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidListCommandException();
        }
        if (!checker.isBlank()) { // user's input is "list tasks" for example
            throw new InvalidListCommandException();
        } else { // user's input is "list " with spacings
            assert !checker.isEmpty() : "Checker should not be blank here.";
            return taskList.listTasks();
        }
    }
}
