package duke;

import java.util.ArrayList;
import java.util.List;

import task.Task;

/**
 * Class responsible for adding and deletion of tasks
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes task from Arraylist
     *
     * @param index index of task to remove.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns a new TaskList containing matching tasks
     *
     * @param stringToFind String to match.
     * @return Object of TaskList class.
     */
    public TaskList find(String stringToFind) {
        List<Task> findList = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getName().contains(stringToFind)) {
                findList.add(task);
            }
        }
        return new TaskList(findList);
    }
}

