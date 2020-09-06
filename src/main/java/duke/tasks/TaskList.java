package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * <p>
 * Offers CRUD capabilities.
 * </p>
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskListIn) {
        taskList = taskListIn;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public boolean remove(Task task) {
        return taskList.remove(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public Task update(int index, Task newTask) {
        return taskList.set(index, newTask);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int getRemainingTaskCount() {
        return (int) taskList.stream().filter(x -> !x.isDone()).count();
    }
}
