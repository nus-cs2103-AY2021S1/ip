package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        return this.tasks.get(taskIndex);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getTotalNumberOfTasks() {
        return this.tasks.size();
    }

    public int getCompletedTasks() {
        int completedTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getIsDone()) {
                completedTasks++;
            }
        }
        return completedTasks;
    }

    public int getUncompletedTasks() {
        int uncompletedTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).getIsDone()) {
                uncompletedTasks++;
            }
        }
        return uncompletedTasks;
    }

    public List<Task> getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are all your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescriptionInListFormat = String.format("%d) %s\n", i + 1, tasks.get(i).toString());
            stringBuilder.append(taskDescriptionInListFormat);
        }
        String completedTasks = getCompletedTasks() + (this.isPluralCompletedTasks() ? " tasks" : " task");
        String uncompletedTasks = getUncompletedTasks() + (this.isPluralUncompletedTasks() ? " tasks." : " task");
        stringBuilder.append("You have completed " + completedTasks + " and have yet to complete "
                + uncompletedTasks);
        return this.tasks;
    }

    public boolean isPluralCompletedTasks() {
        return getCompletedTasks() > 2;
    }

    public boolean isPluralUncompletedTasks() {
        return getUncompletedTasks() > 2;
    }

    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getContent().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public List<Task> findTasksByDate(LocalDate queryDate) {
        List<Task> tasksForQueryDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof TimeBased) {
                TimeBased timeBasedTask = (TimeBased) task;
                LocalDate taskLocalDateTime = timeBasedTask.getDate();
                if (queryDate.isEqual(taskLocalDateTime)) {
                    tasksForQueryDate.add(task);
                }
            }
        }
        return tasksForQueryDate;
    }

    public void markTaskAsDone(int taskIndex) {
        Task updatedTask = tasks.get(taskIndex - 1);
        updatedTask.markTaskAsDone();
        tasks.set(taskIndex - 1, updatedTask);
    }
}
