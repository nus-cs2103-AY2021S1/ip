package duke;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    public int getSize() { return this.taskList.size();}

    public Task get(int i) { return this.taskList.get(i); }
    
}
