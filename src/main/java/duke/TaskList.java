package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents the task list.
 */
public class TaskList {
    /** the lists of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param tasks tasks to be inserted in the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets all the tasks in the list.
     * @return the tasks in TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a particular Task from the TaskList.
     * @param index the index of the task in the list.
     * @return the Task object in the specified index in the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of Task in TaskList.
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a particular Task from the TaskList as done.
     * @param index the index of the task in the list.
     */
    public void markDone(int index) {
        tasks.get(index).completeTask();
    }

    /**
     * Attaches tag to a task.
     * @param index the index of the task in the list.
     * @param tag tag to be attached to the task.
     */
    public void tagTask(int index, String tag) {
        tasks.get(index).setTag(tag);
    }

    /**
     * Removes a particular Task from the TaskList.
     * @param index the index of the task in the list.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Removes all the Task from the TaskList.
     */
    public void removeAllTasks() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a Task into the TaskList.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Searches tasks containing the search term.
     * @param searchTerm the term used to search task.
     * @return list of tasks containing the search term.
     */
    public ArrayList<Task> find(String searchTerm) {
        Predicate<Task> match = task -> {
            return task.getDescription().contains(searchTerm) || task.getTag().contains(searchTerm);
        };
        List<Task> results = new ArrayList<>(tasks)
            .stream()
            .filter(match)
            .collect(Collectors.toList());
        ArrayList<Task> searchResults = new ArrayList<>(results);
        return searchResults;
    }
}
