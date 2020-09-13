package rogue.model.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Wraps a list of {@code Task}.
 *
 * Provides bookkeeping functionalities to manage {@code Task} objects.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList}.
     *
     * @param tasks A list of task to initialize the {@code TaskList}.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds a {@code Task} to the {@code TaskList}.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves {@code Task} corresponding to provided index from the list.
     *
     * @param index Index of the task to retrieve.
     * @throws IndexOutOfBoundsException when index is out of range.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Deletes {@code Task} corresponding to provided index from the list.
     *
     * @param index Index of the task to delete.
     * @throws IndexOutOfBoundsException when index is out of range.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Finds {@code Task} with descriptions containing the search term.
     *
     * @param searchTerm The search term.
     * @return A {@code TaskList} for all matching {@code Task}
     */
    public TaskList searchByDescription(String searchTerm) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(searchTerm))
                .collect(Collectors.toList());

        return new TaskList(matchingTasks);
    }

    /**
     * Finds {@code Task} that are due to happen in the specified number of days.
     *
     * Overdue {@code Task} are not included in the search.
     *
     * @param numOfDays The number of days.
     * @return A {@code TaskList} for all matching {@code Task}
     */
    public TaskList searchByDays(int numOfDays) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(numOfDays + 1); // Add 1 to include end date

        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDate().isAfter(startDate) && task.getDate().isBefore(endDate))
                .collect(Collectors.toList());

        return new TaskList(matchingTasks);
    }

    /**
     * Retrieves the number of {@code Task} currently in the list.
     *
     * @return Number of tasks in list
     */
    public int count() {
        return tasks.size();
    }
}
