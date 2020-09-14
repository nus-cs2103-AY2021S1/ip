package duke.lists;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * List to keep track of deleted tasks
 */
public class DeletedTaskList {
    
    private final ArrayList<Task> deletedTaskList;

    /**
     * Constructor for DeletedTaskList class
     */
    public DeletedTaskList() {
        this.deletedTaskList = new ArrayList<>();
    }

    /**
     * Adds a deleted task to the list
     * 
     * @param deletedTask deleted task added to the list
     */
    public void addDeletedTask (Task deletedTask) {
        this.deletedTaskList.add(deletedTask);
    }

    /**
     * Returns the latest task added to the list and removes it from the list
     * 
     * @return the latest task added to the list
     */
    public Task getLatestDelete () {
        return this.deletedTaskList.remove(size() - 1);
    }

    /**
     * Returns the size of the list
     * 
     * @return size of the list
     */
    public int size() {
        return this.deletedTaskList.size();
    }

    /**
     * Removes all tasks in the list
     */
    public void clear() {
        this.deletedTaskList.clear();
    }
}
