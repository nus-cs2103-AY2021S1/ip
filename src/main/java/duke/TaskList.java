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