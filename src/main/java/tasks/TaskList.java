package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Clears the task list.
     */
    public void clear() {
        taskList.clear();
    }

    /**
     * Returns the size of task list.
     *
     * @return int size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds all items in a given list to the task list.
     *
     * @param list list of objects to be added to the task list.
     * @return boolean true if added successfully.
     */
    public boolean addAll(List<Task> list) {
        return taskList.addAll(list);
    }

    /**
     * Add a task to the task list.
     *
     * @param task task to be added.
     * @return boolean true if added successfully.
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * Get an item in the task list in the specified position.
     *
     * @param index index of item.
     * @return task task in the given index.
     * @see Task
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a task in the given index.
     *
     * @param positions positions of the tasks to be deleted.
     * @return TaskList of deleted tasks
     * @see Task
     */
    public TaskList delete(Integer ... positions) {
        TaskList deleted = new TaskList();
        Arrays.sort(positions, Collections.reverseOrder());
        for (int pos : positions) {
            deleted.add(taskList.get(pos));
            taskList.remove(pos);
        }
        return deleted;
    }
}
