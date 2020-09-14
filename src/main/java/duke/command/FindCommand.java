package duke.command;

import java.time.LocalDate;
import java.util.Optional;

import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Is responsible for handing commands starting with <code>find</code>.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";

    private static final String DONE_KEYWORD = "done";
    private static final String NOT_DONE_KEYWORD = "not_done";

    private String[] keywords;

    /**
     * Creates a <code>FindCommand</code> object.
     * @param keywords The searching keywords
     */
    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    /**
     * Finds all the tasks that happen or due on that day or containing the keyword(s),
     * and pass them to the <code>Ui</code> to print them out.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasklist object cannot be null";
        assert ui != null : "ui object cannot be null";
        TaskList filteredTasks = tasks;
        for (String keyword : keywords) {
            Optional<LocalDate> optDate = DateParser.parse(keyword);
            if (optDate.isPresent()) {
                LocalDate date = optDate.get();
                filteredTasks = tasks.filter((task) -> {
                    boolean isDeadlineAndDueOnDate = task instanceof Deadline && ((Deadline) task).isDueOn(date);
                    boolean isEventAndOccurOnDate = task instanceof Event && ((Event) task).isOccurringOn(date);
                    boolean containKeyword = task.toString().toLowerCase().contains(keyword.toLowerCase());
                    return isDeadlineAndDueOnDate || isEventAndOccurOnDate || containKeyword;
                });
            } else if (keyword.equals(DONE_KEYWORD) || keyword.equals(NOT_DONE_KEYWORD)) {
                filteredTasks = tasks.filter(task -> {
                    boolean isDone = task.isDone();
                    boolean containKeyword = task.toString().toLowerCase().contains(keyword.toLowerCase());
                    return (keyword.equals(DONE_KEYWORD) ? isDone : !isDone) || containKeyword;
                });
            } else {
                filteredTasks = tasks.filter((task) ->
                        task.toString().toLowerCase().contains(keyword.toLowerCase()));
            }
        }
        return ui.showFindResult(filteredTasks);
    }
}
