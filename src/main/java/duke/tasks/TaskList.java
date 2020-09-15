package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList implements Serializable {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        assert (index >= 0 && index < this.listSize());
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * @param index item index in the tasks' list.
     */
    public void deleteTask(int index) {
        assert (index >= 0 && index < this.listSize());
        this.tasks.remove(index);
    }

    public int listSize() {
        return tasks.size();
    }
}
