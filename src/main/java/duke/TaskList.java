package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list of tasks.
 *
 * @author Tee Kok Siang
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a {@link Task} into the TaskList.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    public void doneTask(int taskNumber) {
        tasks.get(taskNumber - 1).markDone();
    }

    public void priorityTask(int taskNumber, int priorityLevel) {
        tasks.get(taskNumber - 1).markPriority(priorityLevel);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    public int size() {
        return tasks.size();
    }

    public static String getTaskMessage(List<Task> tasks) {
        assert tasks != null : "Tasks should not be null";
        StringBuilder taskMessage = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format("\n\t%d. %s", (i + 1), tasks.get(i));
            taskMessage.append(task);
        }
        return taskMessage.toString();
    }
}
