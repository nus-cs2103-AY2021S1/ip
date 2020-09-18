package duke.tasks;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import duke.utils.PersistentList;
import duke.utils.Store;

/**
 * Manages {@code Task} objects.
 */
public class TaskManager {
    /** List of {@code Task} objects. */
    private final List<Task> tasks = new PersistentList<>("./data/tasks.txt",
            new TypeToken<List<Task>>(){}.getType(),
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
        String template = String.format("%s\n%s", Store.getResourceHandler().getString("taskManager.addTask"),
                Store.getResourceHandler().getString(taskCountKey));
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
        String template = String.format("%s\n%s", Store.getResourceHandler().getString("taskManager.removeTask"),
                Store.getResourceHandler().getString(taskCountKey));
        return MessageFormat.format(template, removedTask, tasks.size());
    }

    /**
     * Marks a {@code Task} as done.
     *
     * @param listIndex the index of the {@code Task} in the {@code TaskManager} list.
     * @return a string representation of the action of marking a {@code Task} as done.
     */
    public String markAsDone(int listIndex) {
        Task updatedTask = tasks.get(listIndex).markAsDone();
        tasks.set(listIndex, updatedTask);
        return String.format("%s\n  %s",
                Store.getResourceHandler().getString("taskManager.markTaskDone"), updatedTask);
    }

    /**
     * Returns a list of upcoming {@code Task}s under the {@code TaskManager}.
     *
     * @return a list of upcoming {@code Task}s under the {@code TaskManager}.
     */
    public String getUpcomingTasks() {
        List<Task> sortedUpcomingTasks = tasks.stream().filter(task -> task instanceof Schedulable)
                .filter(task -> !((Schedulable) task).hasDateTimeElapsed()).filter(task -> !task.isDone())
                .sorted().collect(Collectors.toList());
        StringBuilder formattedList =
                new StringBuilder(Store.getResourceHandler().getString("taskManager.upcomingTasksPrefix") + "\n");
        IntStream.range(0, sortedUpcomingTasks.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, sortedUpcomingTasks.get(i)))
                .forEach(formattedList::append);
        return formattedList.toString();
    }

    /**
     * Returns a list of overdue {@code Task}s under the {@code TaskManager}.
     *
     * @return a list of overdue {@code Task}s under the {@code TaskManager}.
     */
    public String getOverdueTasks() {
        List<Task> sortedOverdueTasks = tasks.stream().filter(task -> task instanceof Schedulable)
                .filter(task -> ((Schedulable) task).hasDateTimeElapsed()).filter(task -> !task.isDone())
                .sorted().collect(Collectors.toList());
        StringBuilder formattedList =
                new StringBuilder(Store.getResourceHandler().getString("taskManager.overdueTasksPrefix") + "\n");
        IntStream.range(0, sortedOverdueTasks.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, sortedOverdueTasks.get(i)))
                .forEach(formattedList::append);
        return formattedList.toString();
    }

    /**
     * Returns a list of {@code Task}s under the {@code TaskManager} that contain any of the specified keywords.
     *
     * @param keywords the keywords that are being searched.
     * @return a list of {@code Task}s under the {@code TaskManager} that contain any of the specified keywords.
     */
    public String getMatchingTasks(String... keywords) {
        List<Task> matchingTasks = tasks.stream().filter(task -> task.matchesKeywords(keywords))
                .collect(Collectors.toList());
        StringBuilder formattedList =
                new StringBuilder(Store.getResourceHandler().getString("taskManager.matchingTasksPrefix") + "\n");
        IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, matchingTasks.get(i)))
                .forEach(formattedList::append);
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
                new StringBuilder(Store.getResourceHandler().getString("taskManager.listTasksPrefix") + "\n");
        IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, tasks.get(i)))
                .forEach(formattedList::append);
        return formattedList.toString();
    }
}
