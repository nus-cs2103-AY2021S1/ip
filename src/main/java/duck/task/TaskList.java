package duck.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import duck.Parser;
import duck.exception.DuckException;




/**
 * Class to handle all operations regarding the current list of tasks
 * by the user.
 * Implements Serializable to be easily stored in a file.
 */
public class TaskList implements Serializable {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Loops through the tasks and extracts their statuses,
     * while prepending their index in the list.
     *
     * @return String array of statuses.
     */
    public String[] getStatuses() {
        String[] statuses = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            statuses[i] = "" + (i + 1) + ". " + tasks.get(i).getStatus();
        }
        return statuses;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public int getLength() {
        return tasks.size();
    }

    /**
     * Checks the index to ensure that it is correct before
     * marking the Task as done.
     * Returns the Task recently marked done to display to user.
     *
     * @param index Index of the task to be marked as done.
     * @return Task that is marked done.
     * @throws DuckException If invalid index is given.
     */
    public Task markDone(int index) throws DuckException {
        if (index > tasks.size() || index < 1) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = tasks.get(index - 1);
            task.markDone();
            return task;
        }
    }

    /**
     * Checks the index to ensure that it is correct before
     * deleting the Task
     * Returns the Task recently deleted to display to user.
     *
     * @param index Index of the task to be deleted.
     * @return Task that is deleted.
     * @throws DuckException If invalid index is given.
     */
    public Task deleteTask(int index) throws DuckException {
        if (index > tasks.size() || index < 1) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            return task;
        }
    }

    /**
     * Filters the current list of tasks for tasks that have a date.
     * The tasks are then sorted by due date.
     * Optionally sets a maximum date where all tasks with due dates
     * earlier than the maximum date will be returned.
     *
     * @param optionalDate Maximum date to query for tasks.
     * @return String array of statuses of tasks matching the filters.
     */
    public String[] getStatusesByDate(Optional<LocalDate> optionalDate) {

        return tasks.stream()
                .filter(TaskWithDate.class::isInstance)
                .map(TaskWithDate.class::cast)
                .sorted(Comparator.comparing(TaskWithDate::getDate))
                .filter((task) -> {
                    if (optionalDate.isPresent()) {
                        return task.getDate().isBefore(optionalDate.get());
                    } else {
                        return true;
                    }

                })
                .map(Task::getStatus)
                .toArray(String[]::new);
    }

    /**
     * Takes in a search input and parses for the description to filter by.
     * Filters the list of Tasks based on their description and returns the
     * statuses of those that match.
     *
     * @param input Input from user.
     * @return String array containing statuses of tasks that match.
     * @throws DuckException If description field is empty.
     */
    public String[] getStatusesByFind(String input) throws DuckException {
        String description = Parser.parseDescription(input);
        return tasks.stream()
                .filter((task) -> task.getDescription().contains(description))
                .map(Task::getStatus)
                .toArray(String[]::new);
    }
}
