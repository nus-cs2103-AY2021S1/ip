package duke.Task;

import duke.Exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the requested <code>Task</code> Object from the list
     *
     * @param taskId The 1-based index of the task to be fetched
     * @return The requested <code>Task</code> object
     * @throws DukeException If an invalid taskId is passed in
     */
    public Task get(int taskId) throws DukeException {
        try {
            return tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Task No.%d is not in your list. "
                    + "Please enter a valid task ID.",
                    taskId));
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the requested <code>Task</code> Object from the list
     *
     * @param taskId The 1-based index of the task to be fetched
     * @return The requested <code>Task</code> object
     * @throws DukeException If an invalid taskId is passed in
     */
    public Task remove(int taskId) throws DukeException {
        Task task = get(taskId);
        tasks.remove(task);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
