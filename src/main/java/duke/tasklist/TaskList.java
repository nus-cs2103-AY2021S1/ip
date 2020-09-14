package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Keeps and handles all operations regarding the user tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes the task list with an {@code ArrayList} of tasks.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Initializes the task list with an empty {@code ArrayList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the task at the index.
     *
     * @param index Position of task in list.
     * @return Task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the tasks at the index.
     *
     * @param index Position of task in the list.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task Task being added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the list object of the tasks.
     *
     * @return List object containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if digit lies within (0, number of tasks).
     *
     * @param digit Input digit.
     * @return True if digit lies within the range, false otherwise.
     */
    public boolean checkIfValid(int digit) {
        return digit <= tasks.size() && digit > 0;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True iff task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds all tasks with descriptions that are matching the String input.
     *
     * @param input Input word for querying.
     * @return New TaskList with tasks matching the input word
     */
    public TaskList matchAll(String input) {
        TaskList taskList = new TaskList();
        tasks.stream()
            .filter(task -> task.match(input))
            .forEach(taskList::add);
        return taskList;
    }
}
