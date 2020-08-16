package tasks;

import utils.ResourceHandler;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages {@code Task} objects.
 */
public class TaskManager {
    /** List of {@code Task} objects. */
    private final List<Task> tasks = new ArrayList<>();

    /** Constructs a {@code TaskManager} object. */
    public TaskManager() {}

    /**
     * Adds a new {@code Task} to the {@code TaskManager}.
     *
     * @param task the {@code Task} object to be added.
     * @return a string representation of the action of adding a {@code Task}.
     */
    public String addTask(Task task) {
        tasks.add(task);
        String key = tasks.size() == 1 ? "taskManager.addTask.singular" : "taskManager.addTask.plural";
        return MessageFormat.format(ResourceHandler.getString(key), task, tasks.size());
    }

    /**
     * Mark a {@code Task} as done.
     *
     * @param listIndex the index of the {@code Task} in the {@code TaskManager} list.
     * @return a string representation of the action of marking a {@code Task} as done.
     */
    public String markAsDone(int listIndex) {
        Task updatedTask = tasks.get(listIndex).markAsDone();
        tasks.set(listIndex, updatedTask);
        return String.format("%s\n  %s", ResourceHandler.getString("taskManager.markTaskDone"), updatedTask);
    }

    /**
     * Returns a list of {@code Task}s under the {@code TaskManager}.
     *
     * @return a string representation of the {@code TaskManager}.
     */
    @Override
    public String toString() {
        StringBuilder formattedList =
                new StringBuilder(ResourceHandler.getString("taskManager.listTasksPrefix") + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            formattedList.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return formattedList.toString();
    }
}
