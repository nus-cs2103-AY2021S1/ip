package seedu.bob.commands;

import seedu.bob.data.task.Tasklist;
import seedu.bob.exceptions.BobEmptyFindException;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

/**
 * Finds all tasks containing specific keyword.
 */
public class FindCommand extends Command {
    String input;

    /**
     * Creates a find command.
     *
     * @param input User input.
     * @throws BobEmptyFindException If user input is empty.
     */
    public FindCommand(String input) throws BobEmptyFindException {

        //Removes all whitespaces and checks if input is empty
        if (input.replaceAll("\\s+","").length() == 0) {
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
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        String tasksFound = tasks.findTasks(this.input);

        // Ui shows message to user based on number of tasks found
        if (tasksFound.length() == 0) {
            ui.showNoTaskFoundMessage(this.input);
        } else {
            ui.showTasksFoundMessage(input, tasksFound);
        }
    }
}
