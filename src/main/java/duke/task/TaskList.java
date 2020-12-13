package duke.task;

import java.util.ArrayList;
import java.util.function.Predicate;

import duke.exception.DukeException;

public class TaskList {
    /** The list of tasks */
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty list for tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Initializes the given list of tasks for the TaskList */
    public TaskList(ArrayList<Task> tasksForInit) {
        tasks = tasksForInit;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Sets the task at index as done.
     *
     * @param index The index of the task in the list.
     */
    public void setTaskDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Returns the task at the input index.
     *
     * @param index The index of the task in the list.
     * @return The Task corresponding to the index.
     * @throws DukeException If index is out of bounds.
     */
    public Task getTask(int index) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("There are no tasks at index " + (index + 1));
        } else {
            return tasks.get(index);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int numberOfTasks() {
        return tasks.size();
    }


    /**
     * Returns a list of tasks that follows the condition of the predicate parameter
     *
     * @param tester The predicate for the returned list's tasks to follow
     * @return A list of tasks that returns true with the predicate

     */
    public ArrayList<Task> findTasks(Predicate<Task> tester) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (tester.test(task)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public void updateTaskList(TaskList newTaskList) {
        tasks = newTaskList.tasks;
    }
}
