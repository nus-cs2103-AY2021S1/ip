package ultron;

import ultron.tasks.Task;

import java.util.ArrayList;

public final class TaskList {

    /**
     * Store the internal list of tasks
     */
    private final ArrayList<Task> list;

    /**
     * Task list.
     * It stores all of the tasks present.
     * @param taskArrayList Arraylist containing all the tasks
     */
    public TaskList(final ArrayList<Task> taskArrayList) {

        //Set the arraylist to the list
        list = taskArrayList;
    }

    /**
     * Returns the arraylist of tasks.
     * @return ArrayList containing all of the tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Gets the size of the tasklist.
     * @return int size
     */
    public int size() {

        //Get the size of the list
        return this.list.size();
    }

    /**
     * Mark a particular task as done.
     * @param index The index of the task to be marked
     * @return boolean depending on if the operation was successful
     */
    public boolean markDone(final int index) {

        //Check if the index is valid
        if (index < this.list.size() && index >= 0) {

            //Mark the task at index as done
            this.get(index).markDone();

            //Return true if operation is successful
            return true;

        } else {

            //Otherwise operation will fail
            return false;
        }

    }

    /**
     * Remove a task from the tasklist.
     * @param index Index of the task to be removed
     * @return  Boolean depending on if the operation was successful
     */
    public boolean remove(final int index) {
        //Check if the index is valid
        if (index < this.list.size() && index >= 0) {

            //Remove the task at index
            this.list.remove(index);

            //Return true if operation is successful
            return true;

        } else {

            //Otherwise operation will fail
            return false;
        }
    }

    /**
     * Get the task at index.
     * @param index Index of the task
     * @return  Task at the index provided
     */
    public Task get(final int index) {

        //Return the index of the arraylist
        return this.list.get(index);
    }

    /**
     * Add the task to the tasklist.
     * @param task Task to be added
     */
    public void add(final Task task) {

        //Add the task to the list
        this.list.add(task);
    }
}
