package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks. A <code>TaskManager</code> object will be able to carry out certain functions
 * with regards to the list of tasks.
 */

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    public int getTotalNumberOfTasks() {
        return this.tasks.size();
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int getNumberOfCompletedTasks() {
        int completedTasks = (int) tasks.stream()
                .filter(Task::getIsDone)
                .count();
        return completedTasks;
    }

    public int getNumberOfUncompletedTasks() {
        int uncompletedTasks = (int) tasks.stream()
                .filter(task -> !task.getIsDone())
                .count();
        return uncompletedTasks;
    }

    public List<Task> getAllUncompletedTasks() {
        return tasks.stream()
                .filter(task -> !task.getIsDone())
                .collect(Collectors.toList());
    }

    public List<Task> getAllCompletedTasks() {
        return tasks.stream()
                .filter(task -> task.getIsDone())
                .collect(Collectors.toList());
    }

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

    public List<Task> findTasksByKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.getContent().contains(keyword))
                .collect(Collectors.toList());
    }

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

    public void markTaskAsDone(int taskIndex) {
        Task updatedTask = tasks.get(taskIndex - 1);
        updatedTask.markTaskAsDone();
        tasks.set(taskIndex - 1, updatedTask);
    }

    public void setTagToTask(int taskIndex, String tagName) {
        Task task = tasks.get(taskIndex - 1);
        task.setTag(tagName);
        tasks.set(taskIndex - 1, task);
    }
}
