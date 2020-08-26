package duke;

import java.util.ArrayList;

/**
 * Encapsulates an ArrayList containing the tasks.
 */
public class TaskList {
    ArrayList<Task> tasksList;

    /**
     * Instantiates TaskList containing empty ArrayList.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Instantiates TaskList with existing tasks saved in hard disk.
     * @param tasksList Existing ArrayList.
     */
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Returns the ArrayList contained in the TaskList.
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    /**
     * Sets the completion status of the task to be true.
     *
     * @param idx Index of the given task.
     * @return Task that has just been set to completed.
     * @throws DukeException If task does not exist.
     */
    public Task markAsDone(int idx) throws DukeException {
        if (idx < 0 || idx > getSize() - 1) {
            throw new DukeException("Sorry, the task does not exist");
        } else {
            Task task = tasksList.get(idx);
            task.setDone();
            return task;
        }
    }

    /**
     * Adds a given Task to the ArrayList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasksList.add(task);
    }

    /**
     * Deletes a Task from the ArrayList.
     *
     * @param idx Task to be deleted.
     * @return Task that was deleted from the ArrayList.
     * @throws DukeException If task does not exist.
     */
    public Task deleteTask(int idx) throws DukeException {
        if (idx < 0 || idx > getSize() - 1) {
            throw new DukeException("Sorry, the task does not exist");
        } else {
            Task task = tasksList.get(idx);
            tasksList.remove(idx);
            return task;
        }
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int getSize() {
        return tasksList.size();
    }
}
