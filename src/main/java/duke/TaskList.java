package duke;

import java.util.ArrayList;

/**
 * List class to store tasks
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * TaskList Constructor
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Marks task as done, then returns it
     *
     * @param index Index of task to be completed
     */
    public Task markDone(int index) {
        Task doneTask = list.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    /**
     * Deletes task, then returns it
     *
     * @param index Index of task to be deleted
     */
    public Task delete(int index) {
        Task deletedTask = list.get(index);
        list.remove(index);
        return deletedTask;
    }
}
