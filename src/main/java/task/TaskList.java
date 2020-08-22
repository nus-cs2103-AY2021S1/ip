package task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getCurrCapacity() {
        return this.taskList.size();
    }

    public boolean isValidIndex(int index) {
        return index <= this.taskList.size() && index > 0;
    }

    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    public Task completeTask(int index) {
        Task task = this.taskList.get(index - 1);
        task.completeTask();
        return task;
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    public void printTaskList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            String output = String.format("%d. %s", i + 1, this.taskList.get(i));
            System.out.println(output);
        }
    }
}
