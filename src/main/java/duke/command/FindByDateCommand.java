package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;

import java.util.function.Predicate;

import java.util.stream.Collectors;

/**
 * Represents a command to search for tasks by date.
 */
public class FindByDateCommand extends Command {

    /** Parsed commands containing the search date. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new FindByDateCommand object.
     *
     * @param parsedCommand String array that contains the search date input.
     */
    public FindByDateCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Performs the operation of searching for all the tasks in the user's list
     * of tasks that matches the date provided for the search.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing a list of tasks that matches the search date.
     * @throws DukeException If no tasks could be found due to invalid date provided.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String date = parsedCommand[1].trim();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate dateToSearch = LocalDate.parse(date, dateFormatter);
            Predicate<Task> searchDateFilter = task -> {
                return task.getDate() != null && task.getDate().isEqual(dateToSearch);
            };
            List<Task> searchResults = new ArrayList<>(tasks.getTaskList())
                    .stream()
                    .filter(searchDateFilter)
                    .collect(Collectors.toList());
            return ui.showTaskList(searchResults);
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No task date provided. Please input a valid date using the format: 'dd/mm/yyyy' ";
            throw new InvalidFunctionException(err);
        } catch (DateTimeParseException ex) {
            String err = "The task date format is incorrect. "
                    + "Please input a valid date using the format: 'dd/mm/yyyy'";
            throw new InvalidFunctionException(err);
        }
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
