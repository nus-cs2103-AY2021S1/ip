package duke;

import java.util.LinkedList;

import duke.task.Task;

/**
 * A class to handle operations on the task list.
 * A class that abstracts all operations regarding the task list, so that outer classes
 * do not need to operate on a <code>LinkedList</code> anymore.
 */
public class TaskList {
    private LinkedList<Task> list;

    /**
     * Construct a new <code>TaskList</code> that stores a specified list of tasks.
     * @param list the list of tasks to be stored in the created object
     */
    public TaskList(LinkedList<Task> list) {
        this.list = list;
    }

    /**
     * Construct a new <code>TaskList</code> storing a new empty list of tasks.
     */
    public TaskList() {
        this.list = new LinkedList<>();
    }

    /**
     * Return the list stored in the <code>TaskList</code> object.
     * @return a <code>LinkedList</code> of tasks that is currently stored in the object
     */
    public LinkedList<duke.task.Task> getList() {
        return list;
    }

    /**
     * Return the size of the list stored in the <code>TaskList</code> object.
     * @return an <code>int</code> that is the size of the list that is currently stored
     *         in the object.
     */
    public int size() {
        return list.size();
    }

    /**
     * Mark a specified task in the current list done.
     * Mark a specified task in the current list as done. This index must be verified to
     * be valid for the current list.
     * @param index a valid index indicating the task that needs to be marked as done
     */
    public void markAsDone(int index) {
        list.get(index).markAsDone();
    }

    /**
     * Remove a specified task in the current list.
     * Remove a specified task in the current list. This index must be verified to be valid
     * for the current list.
     * @param index a valid index indicating the task that needs to be removes.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Add a given task to the current list.
     * Add a given task to the current list. This task must be verified to be valid for the
     * current list.
     * @param newTask a new task to be adding to the current list.
     */
    public void add(Task newTask) {
        list.add(newTask);
    }
}
