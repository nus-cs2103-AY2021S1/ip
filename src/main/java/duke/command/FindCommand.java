package duke.command;

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
    public static final String COMMAND = "find";
    
    private String[] keywords;

    /**
     * Creates a <code>FindCommand</code> object.
     * @param keywords The searching keywords
     */
    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    /**
     * Finds all the tasks that happen or due on that day or contaning the keyword,
     * and pass them to the <code>Ui</code> to print them out.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks;
        for (String keyword : keywords) {
            Optional<LocalDate> optDate = DateParser.parse(keyword);
            if (optDate.isPresent()) {
                LocalDate date = optDate.get();
                filteredTasks = tasks.filter((task) -> ((task instanceof Deadline && ((Deadline) task).isDueOn(date))
                                || (task instanceof Event && ((Event) task).isOccuringOn(date))));

            } else {
                filteredTasks = tasks.filter((task) ->
                        task.toString().toLowerCase().contains(keyword.toLowerCase()));
            }
        }
        return ui.showFindResult(filteredTasks);
    }
}
