package viscount;

import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.task.Task;

import java.util.List;

public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to task list.
     * 
     * @param task Task added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks task as done by its index in the list.
     * 
     * @param taskIndex Index of task marked.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size.
     */
    public void markDone(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            Task task = tasks.get(taskIndex);
            task.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Removes task from list by its index in the list.
     * 
     * @param taskIndex Index of task removed.
     * @return Task removed.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     */
    public Task remove(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Gets task list.
     * 
     * @return Task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets task by its index in the list.
     * 
     * @param taskIndex Index of task.
     * @return Task with index taskIndex.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     */
    public Task getTask(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            return tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Gets size of task list.
     * 
     * @return Size of task list.
     */
    public int getTasksSize() {
        return tasks.size();
    }
}
