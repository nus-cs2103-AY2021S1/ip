import task.Task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public void delete(int taskNum) {
        this.taskList.remove(taskNum - 1);
    }

    public Task get(int taskNum) {
        return this.taskList.get(taskNum - 1);
    }

    public int size() {
        return this.taskList.size();
    }
}
