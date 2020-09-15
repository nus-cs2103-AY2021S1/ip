package bob.commands;

import bob.common.MsgGenerator;
import bob.data.task.Tasklist;
import bob.exceptions.BobEmptyFindException;
import bob.storage.Storage;

/**
 * Finds all tasks containing specific keyword.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Creates a find command.
     *
     * @param input User input.
     * @throws BobEmptyFindException If user input is empty.
     */
    public FindCommand(String input) throws BobEmptyFindException {
        //Removes all whitespaces and checks if input is empty
        boolean isEmpty = input.trim().length() == 0;
        boolean isNotEmpty = !isEmpty;

        if (isEmpty) {
            throw new BobEmptyFindException();
        }

        assert isNotEmpty;
        this.input = input.trim();
    }
    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Executes find command.
     *
     * @param tasks Bob's tasklist.
     * @param storage Bob's storage.
     * @return String message response containing find results.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        String tasksFound = tasks.findTasks(this.input);
        // Ui shows message to user based on number of tasks found
        if (tasksFound.length() == 0) {
            return MsgGenerator.generateNoTaskFoundMessage(this.input);
        } else {
            return MsgGenerator.generateTasksFoundMessage(input, tasksFound);
        }
    }
}
