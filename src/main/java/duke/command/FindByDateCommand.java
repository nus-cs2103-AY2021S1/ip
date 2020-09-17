package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to search for tasks by date.
 */
public class FindByDateCommand extends Command {

    /** String containing the search date. */
    private final String searchDate;

    /**
     * Creates and initialises a new FindByDateCommand object.
     *
     * @param searchDate String containing the search date.
     */
    public FindByDateCommand(String searchDate) {
        this.searchDate = searchDate;
        assert !searchDate.isBlank() : "search date cannot be empty";
    }

    /**
     * Performs the operation of searching for all the tasks in the user's list
     * of tasks that matches the date provided for the search.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing a list of tasks that matches the search date.
     * @throws DukeException If an invalid date was provided by the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate dateToSearch = LocalDate.parse(this.searchDate, dateFormatter);
            Predicate<Task> searchDateFilter = task -> {
                return task.getDate() != null && task.getDate().isEqual(dateToSearch);
            };
            List<Task> searchResults = new ArrayList<>(tasks.getTaskList())
                    .stream()
                    .filter(searchDateFilter)
                    .collect(Collectors.toList());
            return ui.showTaskList(searchResults);
        } catch (DateTimeParseException ex) {
            String error = "The task date format is incorrect. "
                    + "Please input a valid date using the format: 'dd/mm/yyyy'";
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
