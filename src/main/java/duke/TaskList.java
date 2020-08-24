package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
