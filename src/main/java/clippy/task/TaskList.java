package clippy.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks managed by Clippy.
 */
public class TaskList {
    private ArrayList<Task> innerList;

    /**
     * Constructs a empty TaskList object.
     */
    public TaskList() {
        innerList = new ArrayList<>();
    }

    /**
     * Constructs a pre-filled TaskList object.
     * 
     * @param tasks ArrayList of tasks to be loaded into TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        innerList = tasks;
    }

    /**
     * Adds the given task into the task list.
     * 
     * @param newTask Task to be added into the task list.
     */
    public void add(Task newTask) {
        innerList.add(newTask);
    }

    /**
     * Deletes the task at the given index (as per full list).
     * 
     * @param indexOfTaskToDelete Index of the task to be deleted (as per full list).
     */
    public void delete(int indexOfTaskToDelete) {
        innerList.remove(indexOfTaskToDelete - 1);
    }

    /**
     * Returns the number of tasks stored within the task list.
     * 
     * @return Number of tasks stored within the task list.
     */
    public int getSize() {
        return innerList.size();
    }

    /**
     * Returns the task at the specified index in the full task list (starts from 1).
     * 
     * @param i Index of task to be retrieved (starts from 1).
     * @return Task at the specified index in the full task list.
     */
    public Task getTask(int i) {
        return innerList.get(i - 1);
    }

    /**
     * Updates the internal full list indices of all tasks within the task list.
     */
    public void updateAllTaskIndices() {
        for (int i = 0; i < innerList.size(); i++) {
            Task t = innerList.get(i);
            t.setIndex(i + 1);
        }
    }

    /**
     * Returns true if there are no tasks in the task list.
     * @return True if there are no tasks in the task list.
     */
    public boolean isEmpty() {
        return innerList.isEmpty();
    }
    
}
