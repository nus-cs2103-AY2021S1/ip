package duke;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskNum) {
        int index = taskNum - 1;
        return tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public Task getTask(int taskNum) {
        int index = taskNum - 1;
        return tasks.get(index);
    }

    public void markAsDone(int taskNum) {
        int index = taskNum - 1;
        tasks.get(index).markAsDone();
    }

    /**
     * Retrieves the list of tasks in the task list which contain the keyword.
     *
     * @param keyword the keyword used to find the matching tasks.
     * @return the list of matching tasks.
     */
    public TaskList getMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                matchingTasks.addTask(tasks.get(i));
            }
        }
        return matchingTasks;
    }

    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            int index = i - 1;
            allTasks.append("\n").append(i).append(".").append(tasks.get(index));
        }
        return allTasks.toString();
    }
}