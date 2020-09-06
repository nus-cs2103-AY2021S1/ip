package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeInvalidDeadlineTimeException;
import duke.exceptions.DukeInvalidEventTimeException;
import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeInvalidKeywordException;
import duke.exceptions.DukeInvalidScheduleInputException;
import duke.exceptions.DukeInvalidTaskDescriptionException;
import duke.exceptions.DukeInvalidTaskTimeException;

/**
 * Contains the task list.
 * Also handles any operation that deals with this list.
 * (for example: <code>addTask</code>, <code>deleteTask</code>)
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Class constructor with a pre-loaded list of tasks.
     *
     * @param tasks The task list to be loaded.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Class constructor with an empty starting task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A getter method that returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Marks a task as complete according to the string input.
     *
     * @param input A string input representing the specific task to complete.
     * @return The task that has been completed itself.
     * @throws DukeInvalidIndexException If the task number provided is invalid.
     */
    public Task completeTask(String input) throws DukeInvalidIndexException {
        try {
            // Obtain index within list of tasks
            int index = Integer.parseInt(input.substring(5)) - 1;

            Task task = tasks.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }

    /**
     * Deletes a task according to the string input.
     *
     * @param input A string input representing the specific task to delete.
     * @return The task that has been deleted itself.
     * @throws DukeInvalidIndexException If the task number provided is invalid.
     */
    public Task deleteTask(String input) throws DukeInvalidIndexException {
        try {
            // Obtain index within list of tasks
            int index = Integer.parseInt(input.substring(7)) - 1;

            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }

    /**
     * Clears all tasks in the task list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Adds a task to the pre-existing list of tasks according to the string input.
     *
     * @param tag   A string with a specific tag. (<code>todo</code>,
     *              <code>event</code> and <code>deadline</code>)
     * @param input A string input representing the specific task to add.
     * @return The task that has been added itself.
     * @throws DukeInvalidTaskDescriptionException If the task description is empty.
     * @throws DukeInvalidTaskTimeException If the task time details are invalid.
     */
    public Task addTask(String tag, String input)
            throws DukeInvalidTaskDescriptionException, DukeInvalidTaskTimeException {
        Task toAdd = null;
        try {
            switch (tag) {
            case "todo":
                toAdd = addToDo(input);
                break;
            case "event":
                toAdd = addEvent(input);
                break;
            case "deadline":
                toAdd = addDeadline(input);
                break;
            default:
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidTaskDescriptionException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidTaskTimeException();
        }
        return toAdd;
    }

    /**
     * Adds a todo to the list of tasks.
     *
     * @param input A string representing the todo to be added.
     * @return The todo that has been added itself.
     */
    private Task addToDo(String input) {
        String toDoText = input.substring(5);
        ToDo toDo = new ToDo(toDoText);
        tasks.add(toDo);
        return toDo;
    }

    /**
     * Finds all the tasks which match the keyword in the user input.
     *
     * @param input A string representing the user input.
     * @return The list of tasks which match the keyword in the user input.
     * @throws DukeInvalidKeywordException If the keyword input is invalid or empty.
     */
    public List<Task> findTasks(String input) throws DukeInvalidKeywordException {
        String keyword;

        try {
            keyword = input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidKeywordException();
        }

        return tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Finds all the tasks whose date matches the date in the user input.
     *
     * @param input A string representing the user input.
     * @return The list of tasks whose date matches the date in the user input.
     * @throws DukeInvalidScheduleInputException If the date input is invalid or empty.
     */
    public List<Task> findScheduledTasks(String input) throws DukeInvalidScheduleInputException {
        String dateInput;
        LocalDate date;

        try {
            dateInput = input.substring(9);
            date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeInvalidScheduleInputException();
        }

        return tasks.stream()
                .filter(t -> t.getDate().equals(date))
                .sorted(Comparator.comparing(Task::getTime))
                .collect(Collectors.toList());
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param input A string representing the event to be added.
     * @return The event that has been added itself.
     * @throws DukeInvalidEventTimeException If the event time details are invalid.
     */
    private Task addEvent(String input) throws DukeInvalidEventTimeException {
        String[] eventText = input.substring(6).split(" /at ");
        String eventDescription = eventText[0];

        if (eventText.length == 1) {
            throw new DukeInvalidEventTimeException();
        }

        String eventAt = eventText[1];
        Event event = new Event(eventDescription, eventAt);
        tasks.add(event);
        return event;
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param input A string representing the deadline to be added.
     * @return The deadline that has been added itself.
     * @throws DukeInvalidDeadlineTimeException If the deadline time details are invalid.
     */
    private Task addDeadline(String input) throws DukeInvalidDeadlineTimeException {
        String[] deadlineText = input.substring(9).split(" /by ");
        String deadlineDescription = deadlineText[0];

        if (deadlineText.length == 1) {
            throw new DukeInvalidDeadlineTimeException();
        }

        String deadlineBy = deadlineText[1];
        Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(deadline);
        return deadline;
    }

}
