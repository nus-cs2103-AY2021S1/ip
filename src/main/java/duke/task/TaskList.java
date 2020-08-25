package duke.task;

import java.util.List;

public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList =  taskList;
    }

    public int getListSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public void addTask(Task toAdd) {
        this.taskList.add(toAdd);
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

}
