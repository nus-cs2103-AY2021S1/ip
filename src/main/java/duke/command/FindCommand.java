package duke.command;

import duke.exception.InvalidDateFormatException;
import duke.parser.DateParser;
import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import duke.ui.Ui;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Is responsible for handing commands starting with <code>find</code>.
 */
public class FindCommand extends Command {
    public final static String COMMAND = "find";
    
    private String keyword;

    /**
     * Creates a <code>FindCommand</code> object.
     * @param keyword The searching keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all the tasks that happen or due on that day or contaning the keyword,
     * and pass them to the <code>Ui</code> to print them out.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     * @throws InvalidDateFormatException If the date cannot be parsed
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateFormatException {
        Optional<LocalDate> optDate = DateParser.parse(keyword);
        if (optDate.isPresent()) {
            LocalDate date = optDate.get();
            TaskList filteredTasks = tasks.filter((task) ->
                    ((task instanceof Deadline && ((Deadline) task).isDueOn(date))
                    || (task instanceof Event && ((Event) task).isOccuringOn(date))));
            return ui.showFindResult(filteredTasks);
        } else {
            TaskList filteredTasks = tasks.filter((task) ->
                    task.toString().toLowerCase().contains(keyword.toLowerCase()));
            return ui.showFindResult(filteredTasks);
        }
    }
}
