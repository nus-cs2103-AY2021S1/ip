package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class containing the list of tasks
 * with operations to add or delete tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) throws DukeException {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

}
