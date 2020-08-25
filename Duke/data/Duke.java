package Duke.data;

import Duke.data.task.TaskList;
import Duke.data.task.TaskList.TaskNotFoundException;
import Duke.data.task.Task;

public class Duke {

    private final TaskList taskList;

    /**
     * Creates an empty task list.
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Constructs an task list with the given data.
     *
     * @param tasks external changes to this will not affect this task list
     */
    public Duke(TaskList tasks) {
        this.taskList = new TaskList(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     */
    public void addTask(Task toAdd) {
        taskList.add(toAdd);
    }

    /**
     * Adds a task to the task list.
     *
     */
    public Task markDone(int index) {
        taskList.getTask(index).markAsDone();
        return taskList.getTask(index);
    }

    /**
     * Returns true if an equivalent task exists in the task list.
     */
    public boolean containsTask(Task key) {
        return taskList.contains(key);
    }

    /**
     * Removes the equivalent task from the task list.
     *
     */
    public void removeTask(int toRemove) throws TaskNotFoundException {
        taskList.remove(toRemove - 1);
    }

    /**
     * Returns a new UniquePersonList of all tasks in the task list at the time of
     * the call.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duke // instanceof handles nulls
                        && this.taskList.equals(((Duke) other).taskList));
    }
}
