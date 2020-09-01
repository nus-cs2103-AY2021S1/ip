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
        if (input.replaceAll("\\s+", "").length() == 0) {
            throw new BobEmptyFindException();
        }

        this.input = input.startsWith(" ")
                ? input.substring(1)
                : input;
    }
    @Override
    public boolean isExited() {
        return false;
    }


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
