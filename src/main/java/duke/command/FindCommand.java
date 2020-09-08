package duke.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.parser.FindTaskStringChecker;
import duke.task.TaskList;


/**
 * Represents a Find Command that gives users a way to find a task by searching for a keyword.
 * The Find Command can only match a specified input string with the task names.
 * Dates and times are not used in the matching.
 */
public class FindCommand extends Command {

    /**
     * Initialises the find command with the user input string array.
     *
     * @param userString String array that represents the user input.
     */
    public FindCommand(String[] userString) {
        super(userString);
    }

    /**
     * Finds all matching tasks from the task list array and prints all of them out.
     *
     * @param tasks Task list to operate on.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     * @return String to be printed out.
     * @throws DukeException If find command is not followed by any keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        new FindTaskStringChecker(getStringArray()).checkTaskName();
        String keyword = Arrays.stream(getStringArray()).skip(1).collect(Collectors.joining(" "));
        return ui.printMatches(tasks.findTask(keyword));
    }
}
