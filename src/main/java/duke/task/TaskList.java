package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object with an empty task-list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    /** Returns the tasks in the task-list as an array of string objects. */
    public String[] getTasks() {
        int numTasks = tasks.size();
        String[] taskStrings = new String[numTasks];
        for (int i = 0; i < numTasks; i++) {
            taskStrings[i] = tasks.get(i).toString();
        }
        return taskStrings;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds tasks from the given list to the current task-list.
     *
     * @param tasks The list of tasks to be added to the task-list.
     */
    public void loadTasks(ArrayList<Task> tasks) {
        for (Task t : tasks) {
            this.tasks.add(t);
        }
    }

    /**
     * Marks the task at the specified index of the task-list as done.
     *
     * @param index The index specifying the position in the task-list to mark the task as done (starts at 1).
     * @return The task that has been marked as done.
     * @throws DukeException If the index is out of bounds, either less than 1 or bigger than the size of
     * the task-list.
     */
    public Task markTaskAsDone(int index) throws DukeException {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }

    /**
     * Deletes the task at the specified index of the task-list.
     *
     * @param index The index specifying the position in the task-list to delete the task (starts at 1).
     * @return The deleted task.
     * @throws DukeException If the index is out of bounds, either less than 1 or bigger than the size of
     * the task-list.
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }
}
