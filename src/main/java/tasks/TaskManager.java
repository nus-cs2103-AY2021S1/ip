package tasks;

import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import utils.PersistentList;
import utils.ResourceHandler;
import utils.Schedulable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages {@code Task} objects.
 */
public class TaskManager {
    /** List of {@code Task} objects. */
    private final List<Task> tasks = new PersistentList<>("./data/tasks.txt",
            new TypeToken<ArrayList<Task>>(){}.getType(),
            RuntimeTypeAdapterFactory.of(Task.class)
                    .registerSubtype(Deadline.class)
                    .registerSubtype(Event.class)
                    .registerSubtype(ToDo.class));

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
        String taskCountKey = "taskManager.taskCount." + (tasks.size() == 1 ? "singular" : "plural");
        String template = String.format("%s\n%s", ResourceHandler.getString("taskManager.addTask"),
                ResourceHandler.getString(taskCountKey));
        return MessageFormat.format(template, task, tasks.size());
    }

    /**
     * Removes a {@code Task} from the {@code TaskManager}.
     *
     * @param listIndex the index of the {@code Task} in the {@code TaskManager} list.
     * @return a string representation of the action of removing a {@code Task}.
     */
    public String removeTask(int listIndex) {
        Task removedTask = tasks.remove(listIndex);
        String taskCountKey = "taskManager.taskCount." + (tasks.size() == 1 ? "singular" : "plural");
        String template = String.format("%s\n%s", ResourceHandler.getString("taskManager.removeTask"),
                ResourceHandler.getString(taskCountKey));
        return MessageFormat.format(template, removedTask, tasks.size());
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
     * Returns a list of upcoming {@code Task}s under the {@code TaskManager}.
     *
     * @return a list of upcoming {@code Task}s under the {@code TaskManager}.
     */
    public String getUpcomingTasks() {
        List<Task> sortedUpcomingTasks = tasks.stream().filter(task -> task instanceof Schedulable)
                .filter(task -> !((Schedulable) task).hasDateTimeElapsed()).sorted().collect(Collectors.toList());
        StringBuilder formattedList =
                new StringBuilder(ResourceHandler.getString("taskManager.upcomingTasksPrefix") + "\n");
        for (int i = 0; i < sortedUpcomingTasks.size(); i++) {
            formattedList.append(String.format("%d. %s\n", i + 1, sortedUpcomingTasks.get(i)));
        }
        return formattedList.toString();
    }

    /**
     * Returns a list of overdue {@code Task}s under the {@code TaskManager}.
     *
     * @return a list of overdue {@code Task}s under the {@code TaskManager}.
     */
    public String getOverdueTasks() {
        List<Task> sortedOverdueTasks = tasks.stream().filter(task -> task instanceof Schedulable)
                .filter(task -> ((Schedulable) task).hasDateTimeElapsed()).sorted().collect(Collectors.toList());
        StringBuilder formattedList =
                new StringBuilder(ResourceHandler.getString("taskManager.overdueTasksPrefix") + "\n");
        for (int i = 0; i < sortedOverdueTasks.size(); i++) {
            formattedList.append(String.format("%d. %s\n", i + 1, sortedOverdueTasks.get(i)));
        }
        return formattedList.toString();
    }

    /**
     * Returns a list of {@code Task}s under the {@code TaskManager}.
     *
     * @return a list of {@code Task}s under the {@code TaskManager}.
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
