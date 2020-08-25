import task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public void delete(int taskId) {
        this.taskList.remove(taskId);
    }

    public Task get(int taskId) {
        return this.taskList.get(taskId);
    }

    public int size() {
        return this.taskList.size();
    }
}
