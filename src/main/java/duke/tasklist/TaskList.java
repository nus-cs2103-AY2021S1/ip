package duke.tasklist;

import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;
import duke.tasklist.tasklistfilter.TaskListFilter;


/**
 * Contains the task list and operations.
 */
public class TaskList {
    protected List<Task> tasks;

    /**
     * TaskList constructor.
     *
     * @param planner The list of tasks.
     */
    public TaskList(List<Task> planner) {
        this.tasks = planner;
    }

    /**
     * Adds a task into the planner.
     *
     * @param task Task object.
     */
    public void addToPlanner(Task task) {
        tasks.add(task);
    }

    /**
     * Gives the number of tasks in the planner.
     *
     * @return The number of tasks.
     */
    public Integer getSize() {
        return tasks.size();
    }

    /**
     * Gives the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getPlanner() {
        return this.tasks;
    }

    /**
     * Marks the task as done.
     *
     * @param index The index of the task in the list of tasks.
     * @return The task that is marked as done.
     */
    public Task markAsDone(Integer index) {
        this.tasks.get(index).done();
        return this.tasks.get(index);
    }

    /**
     * Deletes the task.
     *
     * @param index The index of the task in the list of tasks.
     * @return The task deleted.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gives the task.
     *
     * @param index The index of the specified task.
     * @return The task inquired.
     */
    public Task getTask(Integer index) {
        return this.tasks.get(index);
    }

    /**
     * Filters the list of tasks with the keyword.
     *
     * @param listFilter The filter for the tasklist.
     * @return A filtered list of tasks.
     */
    public List<Task> findTasks(TaskListFilter listFilter) {
        /*
        List<Task> filteredTasks = new ArrayList<>();
        // Original version
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }

        // Lambda version
        tasks.forEach(task -> {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        });
        return filteredTasks;

        // Stream Version
        return tasks.stream()
            .filter(task -> task.getDescription().contains(keyword))
            .collect(Collectors.toList());
        */
        return tasks.stream()
                .filter(task -> listFilter.filter(task))
                .collect(Collectors.toList());

    }
}
