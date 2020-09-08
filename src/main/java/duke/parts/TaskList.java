package duke.parts;

import java.util.ArrayList;
import java.util.Collections;

import duke.task.Task;

/**
 * A class to store and handle the tasks input by the user
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void updateList(Storage storage) {
        storage.save(tasks);
    }

    /**
     * Add a task and update the storage of the system.
     * @param task Tasks to be added
     * @param storage Storage of the system
     */
    public void addTask(Task task, Storage storage) {
        tasks.add(task);
        updateList(storage);
    }
    /**
     * Deletes a task and update the storage of the system.
     * @param index Index of task to be deleted
     * @param storage Storage of the system
     */
    public Task deleteTask(int index, Storage storage) {
        assert index < tasks.size() && index >= 0 ;
        Task removed = tasks.remove(index);
        updateList(storage);
        return removed;
    }

    /**
     *
     * @return Number of tasks stored in the list.
     */
    public int numTask() {
        return tasks.size();
    }

    /**
     * Searches the list for tasks that matched the input string.
     * @param input substring that is used in the search.
     * @return An arraylist of tasks that match the input string.
     */
    public ArrayList<Task> find(String input) {
        ArrayList<Task> temp = new ArrayList<>();

        for (Task t: tasks) {
            if (t.contains(input)) {
                temp.add(t);
            }
        }
        return temp;
    }

    public void sort(Storage storage) {
        Collections.sort(tasks, new TaskComparator());
        this.updateList(storage);
    }

}
