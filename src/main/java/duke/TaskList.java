package duke;

import java.util.ArrayList;

/**
 * The TaskList contains the list of tasks and operations to modify the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object with an existing list.
     * @param taskList An arraylist of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for a TaskList object with a new list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the list in the TaskList object.
     * @return An arraylist containing all the Tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Retrieves a task from the TaskList object.
     * @param number index of the task.
     * @return the specified Task object.
     * @throws DukeException if index is out of bounds.
     */
    public Task get(int number) throws DukeException {
        try {
            return taskList.get(number);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide valid index of the task");
        }
    }

    /**
     * Adds a new task to the tasklist.
     * @param task A task object.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Changes the completion status of the Task to completed.
     * @param number index of the Task to be completed.
     */
    public void finishTask(int number) {
        Task task = taskList.get(number);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Removes the task in the tasklist.
     * @param number index of the task to be removed.
     */
    public Task deleteTask(int number) {
        Task task = taskList.get(number);
        taskList.remove(task);
        return task;
    }

    /**
     * Gets the size of the tasklist object.
     * @return integer of the size of the tasklist object.
     */
    public int getSize() {
        return taskList.size();
    }
}
