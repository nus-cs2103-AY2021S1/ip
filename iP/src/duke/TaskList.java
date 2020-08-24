package duke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        Task task = taskList.get(index);
        return task;
    }
}
