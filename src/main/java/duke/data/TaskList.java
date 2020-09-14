package duke.data;

import java.util.ArrayList;
import java.util.List;

import duke.data.task.Task;

/**
 * Represents the entire task list. Contains the data of all the tasks.
 */
public class TaskList {

    private final List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public TaskList(List<Task> list) {
        this.taskList.addAll(list);
    }

    public List<Task> getList() {
        return this.taskList;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(Task targetTask) {
        this.taskList.remove(targetTask);
    }

}
