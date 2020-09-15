package viscount.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import viscount.Parser;
import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountDateTimeParseException;
import viscount.exception.ViscountException;
import viscount.task.Task;
import viscount.task.TaskType;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    private String taskTypeModifier;
    private String dateString;
    private String findString;

    /**
     * Instantiates a new list command.
     *
     * @param taskTypeModifier Task type modifier of the command.
     * @param dateString Date string argument.
     * @param findString Find string argument.
     */
    public ListCommand(String taskTypeModifier, String dateString, String findString) {
        super();
        this.taskTypeModifier = taskTypeModifier;
        this.dateString = dateString;
        this.findString = findString;
    }

    /**
     * Executes the list command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountDateTimeParseException If exception occurs with parsing date string.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountException {
        Predicate<Task> filterByModifier = task -> {
            boolean isEmptyModifier = taskTypeModifier.isEmpty();

            return isEmptyModifier || task.getTaskType() == TaskType.valueOf(taskTypeModifier.toUpperCase());
        };

        Predicate<Task> filterByDescription = task -> task.getDescription().contains(findString);

        List<Task> tasks = taskList.getTasks();

        if (tasks.isEmpty()) {
            return ui.getEmptyListResponse();
        }

        if (dateString.isEmpty()) {
            List<Task> filteredTasks = tasks
                    .stream()
                    .filter(filterByModifier)
                    .filter(filterByDescription)
                    .collect(Collectors.toList());

            return ui.getListResponse(filteredTasks, taskTypeModifier, dateString, findString);
        } else {
            try {
                LocalDateTime queriedDateTime = dateString.equals("today")
                        ? LocalDate.now().atStartOfDay()
                        : Parser.parseDateTime(dateString, Parser.INPUT_DATE_TIME_FORMATTER);

                List<Task> filteredTasks = tasks
                        .stream()
                        .filter(Task::hasDateTime)
                        .filter(filterByModifier)
                        .filter(filterByDescription)
                        .filter(task -> task.getDateTime().toLocalDate().isEqual(queriedDateTime.toLocalDate()))
                        .sorted(Comparator.comparing(Task::getDateTime))
                        .collect(Collectors.toList());

                return ui.getListResponse(
                        filteredTasks,
                        taskTypeModifier,
                        dateString.equals("today")
                                ? dateString
                                : queriedDateTime.format(Parser.OUTPUT_DATE_FORMATTER),
                        findString);
            } catch (DateTimeParseException e) {
                throw new ViscountDateTimeParseException("date query");
            }
        }
    }

    /**
     * Compares this command with another object for equality.
     *
     * @param o Object compared.
     * @return True if this command is equal to the object, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        ListCommand listCommand = (ListCommand) o;
        boolean hasSameTaskTypeModifier = this.taskTypeModifier.equals(listCommand.taskTypeModifier);
        boolean hasSameDateString = this.dateString.equals(listCommand.dateString);
        boolean hasSameFindString = this.findString.equals(listCommand.findString);
        return hasSameTaskTypeModifier && hasSameDateString && hasSameFindString;
    }
}
