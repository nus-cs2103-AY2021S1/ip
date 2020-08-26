package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a Find Command that gives users a way to find a task by searching for a keyword.
 * The Find Command can only match a specified input string with the task names.
 * Dates and times are not used in the matching.
 */
public class FindCommand extends Command {

    /**
     * Initialises the find command with the user input string array.
     *
     * @param stringArray String array that represents the user input.
     */
    public FindCommand(String[] stringArray) {
        super(stringArray);
    }

    /**
     * Finds all matching tasks from the task list array and prints all of them out.
     *
     * @param tasks Task list to operate on.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException If find command is not followed by any keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.isFirstIndexEmpty()) {
            throw new DukeException("Oops, please enter a keyword after your command!");
        }
        String keyword = Arrays.stream(getArray()).skip(1).collect(Collectors.joining(" "));
        ui.printMatches(tasks.findTask(keyword));
    }

}
