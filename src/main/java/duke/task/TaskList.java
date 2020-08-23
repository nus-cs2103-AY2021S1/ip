package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsulates a list of {@link Task}s.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initialises a new instance with no existing {@link Task}s.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises a new instance based on a list of existing {@link Task}s.
     *
     * @param tasks The lists of tasks to initialise the instance with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a {@link Task} stored in the list based on the given task ID.
     *
     * @param taskId The ID of the task to be retrieved.
     */
    public Task getTask(int taskId) {
        return this.tasks.get(taskId - 1);
    }

    /**
     * Adds a {@link Task} to end of the list.
     *
     * @param task The task to be added to the list.
     * @return <code>true</code> if the task was added successfully, <code>false</code> otherwise.
     */
    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    /**
     * Deletes a {@link Task} in the list.
     *
     * @param taskId The ID of the task to be deleted.
     * @return The {@link Task} that was deleted form the list.
     */
    public Task deleteTask(int taskId) {
        return this.tasks.remove(taskId - 1);
    }

    /**
     * Returns the number of {@link Task}s currently stored in the list.
     *
     * @return The number of {@link Task}s currently stored in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    public TaskList search(String keyword) {
        List<Task> filteredList =
                this.tasks.stream().filter((task) -> task.containsKeyword(keyword))
                        .collect(Collectors.toList());
        return new TaskList(filteredList);
    }

    /**
     * Returns a string representation of the list.
     * For each task in the list, the string representation includes a numbering of these tasks
     * based on their position in the list followed by the string representation of the task
     * itself. These strings are separated by a newline.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return sb.toString().trim();
    }

}
