package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks. A {@code TaskManager} object will be able to carry out certain functions
 * with regards to the list of tasks.
 */

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a new {@code Task} to the {@code TaskManager}.
     *
     * @param newTask the new {@code Task} that is to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a {@code Task} from the {@code TaskManager}.
     *
     * @param taskIndex the index of the {@code Task} in the {@code TaskManager}.
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    /**
     * Retrieves the respective {@code Task} from the {@code TaskManager}.
     *
     * @param taskIndex the index of the {@code Task} in the {@code TaskManager}.
     * @return the {@code Task} at the respective index in the {@code TaskManager}.
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    /**
     * Returns the total number of {@code Task} in the {@code TaskManager}.
     *
     * @return the total number of {@code Task} in the {@code TaskManager}.
     */
    public int getTotalNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns the list of {@code Task} in the {@code TaskManager}.
     *
     * @return the list of {@code Task} from the {@code TaskManager}.
     */
    public List<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Returns the total number of completed {@code Task} in the {@code TaskManager}.
     *
     * @return the total number of completed {@code Task} in the {@code TaskManager}.
     */
    public int getNumberOfCompletedTasks() {
        int completedTasks = (int) tasks.stream()
                .filter(Task::getIsDone)
                .count();
        return completedTasks;
    }

    /**
     * Returns the total number of uncompleted {@code Task} in the {@code TaskManager}.
     *
     * @return the total number of uncompleted {@code Task} in the {@code TaskManager}.
     */
    public int getNumberOfUncompletedTasks() {
        int uncompletedTasks = (int) tasks.stream()
                .filter(task -> !task.getIsDone())
                .count();
        return uncompletedTasks;
    }

    /**
     * Returns the list of all uncompleted {@code Task} in the {@code TaskManager}.
     *
     * @return the list of all uncompleted {@code Task} in the {@code TaskManager}.
     */
    public List<Task> getAllUncompletedTasks() {
        return tasks.stream()
                .filter(task -> !task.getIsDone())
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of all completed {@code Task} in the {@code TaskManager}.
     *
     * @return the list of all completed {@code Task} in the {@code TaskManager}.
     */
    public List<Task> getAllCompletedTasks() {
        return tasks.stream()
                .filter(task -> task.getIsDone())
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all {@code Task}, in the {@code TaskManager}, which has a date that is
     * before the query date.
     *
     * @param queryDate the query date which is after the {@code Task} date.
     * @return a list of all {@code Task} which has a date that is before the query date.
     */
    public List<Task> getAllTasksBeforeQueryDate(LocalDate queryDate) {
        List<Task> tasksBeforeQueryDate = new ArrayList<>();
        tasks.forEach(task -> {
            if (task instanceof TimeBased) {
                TimeBased timeBasedTask = (TimeBased) task;
                LocalDate taskLocalDate = timeBasedTask.getDate();
                if (queryDate.isAfter(taskLocalDate)) {
                    tasksBeforeQueryDate.add(task);
                }
            }
        });
        return tasksBeforeQueryDate;
    }

    /**
     * Returns a list of all {@code Task} in the {@code TaskManager} which matches the specified keyword.
     *
     * @param keyword the keyword that is being searched for.
     * @return a list of {@code Task} in the {@code TaskManager} that matches the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.getContent().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of {@code Task} from the {@code TaskManager} which has dates that matches the query date.
     *
     * @param queryDate the date which matches the {@code Task}'s date.
     * @return
     */
    public List<Task> findTasksByDate(LocalDate queryDate) {
        List<Task> tasksForQueryDate = new ArrayList<>();
        tasks.forEach(task -> {
            if (task instanceof TimeBased) {
                TimeBased timeBasedTask = (TimeBased) task;
                LocalDate taskLocalDate = timeBasedTask.getDate();
                if (queryDate.isEqual(taskLocalDate)) {
                    tasksForQueryDate.add(task);
                }
            }
        });
        return tasksForQueryDate;
    }

    /**
     * Marks the specified {@code Task} as completed.
     *
     * @param taskIndex the index of the {@code Task} in the {@code TaskManager}.
     */
    public void markTaskAsDone(int taskIndex) {
        Task updatedTask = tasks.get(taskIndex - 1);
        updatedTask.markTaskAsDone();
        tasks.set(taskIndex - 1, updatedTask);
    }

    /**
     * Sets a tag to the specified {@code Task}.
     *
     * @param taskIndex the index of the {@code Task} in the {@code TaskManager}.
     * @param tagName the name of the tag.
     */
    public void setTagToTask(int taskIndex, String tagName) {
        Task task = tasks.get(taskIndex - 1);
        task.setTag(tagName);
        tasks.set(taskIndex - 1, task);
    }
}
