package duke.tool;

import duke.exception.DukeException;
import duke.exception.TaskExistException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the task list stored in Duke system.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList()  {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks)  {
        taskList = tasks;
    }

    /**
     * Returns the list size of task list.
     * @return Size of current list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds new task into the current list.
     * @param newTask New task to be added to the list.
     * @throws TaskExistException When task's description is the same as some task in list.
     */
    public void add(Task newTask) throws TaskExistException {
        if (this.containsExactDescription(newTask)) {
            throw new TaskExistException();
        }

        this.taskList.add(newTask);
    }

    /**
     * Deletes the certain task with given index.
     * @param index Index of task to be deleted.
     * @return Task that has been deleted.
     */
    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Returns a task list.
     * @return Task list of current object.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Marks the task with given element to done.
     * @param index Index of the element.
     * @return Task that has been marked as done.
     */
    public Task markDone(int index) {
        Task targetTask = taskList.get(index);
        targetTask.markAsDone();
        return targetTask;
    }

    /**
     * Clear all task in current list.
     */
    public void clear() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns whehter there is exact same task in the list.
     * @param targetTask Task that user want to add into list.
     * @return True if there is a task with exact description, false otherwise.
     */
    public boolean containsExactDescription(Task targetTask) {
        for (Task task : taskList) {
            if (task.isExactDescription(targetTask)) {
                return true;
            }
        }

        return false;
    }
}
