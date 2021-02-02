package duke.task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the collection that holds the tasks. Should only be used to
     * iterate over the list.
     *
     * @return Collection of Tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task
     * @return true.
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }

    /**
     * Deletes a task from the list by index.
     *
     * @param index Index of the task to delete.
     * @return The deleted task.
     */
    public Task delete(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of elements of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task by index from the list.
     *
     * @param index Index of the task.
     * @return The specified task.
     */
    public Task get(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Finds matching tasks by keyword and adds them to another TaskList.
     *
     * @param keyword Keyword used to search tasks.
     * @return TaskList of matching tasks.
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    private TaskList filter(Predicate<Task> tester) {
        TaskList filteredList = new TaskList();
        for (Task task : tasks) {
            if (tester.test(task)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    /**
     * Gets a list of upcoming tasks in the week.
     *
     * @return list of upcoming tasks.
     */
    public TaskList getUpcomingTasks() {
        TaskList tasksWithDateTime = filter(task -> task.getTaskType().equals("deadline")
            || task.getTaskType().equals("event"));
        TaskList tasksWithinAWeek = tasksWithDateTime.filter(
            task -> LocalDate.now().until(task.getDate(), ChronoUnit.DAYS) <= 7
                    && LocalDate.now().until(task.getDate(), ChronoUnit.DAYS) >= 0);
        return tasksWithinAWeek;
    }
}
