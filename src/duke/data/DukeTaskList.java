package duke.data;

import java.util.ArrayList;

import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * DukeTaskList manages tasks in Duke.
 */
public class DukeTaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a DukeTaskList.
     */
    public DukeTaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the task with the index.
     * @param index the task index
     * @return the task
     */
    public Task getTask(int index) {
        indexCheck(index);
        return tasks.get(index);
    }

    /**
     * Adds the input task to the current task list.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task with the index from the current task list.
     * @param index the index of the task
     * @return the task deleted
     */
    public Task deleteTask(int index) {
        Task taskDelete = getTask(index);
        tasks.remove(index);

        return taskDelete;
    }

    /**
     * Gets the current number of tasks saved in the task list.
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Overrides all current tasks using the tasks in the input ArrayList of Tasks.
     * @param tasks the ArrayList of tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the ArrayList of current tasks.
     * @return the ArrayList of current tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void indexCheck(int index) {
        if (index >= getSize() || index < 0) {
            throw new InvalidIndexException("Invalid Index!");
        }
    }
}
