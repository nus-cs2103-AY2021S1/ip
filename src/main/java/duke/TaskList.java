package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task markTaskDoneInList(int taskNumber) throws DukeException {
        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new DukeException("Please enter a valid task number\n");
        } else {
            Task task = tasks.get(taskNumber);
            task.markDone();
            return task;
        }
    }

    public Task deleteTaskFromList(int taskNumber) throws DukeException {
        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new DukeException("Please enter a valid task number\n");
        } else {
            Task task = tasks.get(taskNumber);
            tasks.remove(task);
            return task;
        }
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            boolean hasKeyword = task.getDescription().contains(keyword);
            if (hasKeyword) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }
}
