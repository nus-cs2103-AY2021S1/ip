package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.parser.DateTimeStringChecker;
import duke.task.TaskList;

/**
 * Represents a view command that allows the viewing of tasks by schedule.
 */
public class ViewCommand extends Command {

    /**
     * Creates a new View Command and initialises its string array.
     *
     * @param userString Tokenized array form of the input command string.
     */
    public ViewCommand(String[] userString) {
        super(userString);
    }

    /**
     * Extracts tasks that fall on the same day as the specified date.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @return String to be printed out once execution of command is over.
     * @throws DukeException If the date is unspecified or the date format is wrong.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (getStringArray().length < 2) {
            throw new DukeException("Please enter a date to search for!");
        }
        LocalDate date = new DateTimeStringChecker(getStringArray()).checkDate(new String[]{getStringArray()[1]});
        return ui.printMatches(tasks.findTaskByDate(date));
    }
}
