package duckie.task;

import java.util.ArrayList;

import duckie.exception.DuckieException;
import duckie.exception.DuckieNoListException;
import duckie.ui.Ui;

/**
 * Contains all the saved tasks in a List form.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiatess an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiatess a TaskList with pre-saved tasks.
     *
     * @param tasks ArrayList containing pre-saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList containing the current tasks.
     *
     * @return ArrayList containing current tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the actual Index of a task in the ArrayList.
     *
     * @param task Task required.
     * @return Integer index value.
     */
    public int getIndex(Task task) {
        assert task != null : "There is not task to get index from.";
        return tasks.indexOf(task);
    }

    /**
     * Adds a Task to TaskList.
     *
     * @param t1 Task to be added.
     */
    public void addTask(Task t1) {
        tasks.add(t1);
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param ind Index of the Task in ArrayList.
     */
    public void deleteTask(int ind) {
        tasks.remove(ind);
    }

    /**
     * Deletes all Tasks in the TaskList.
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Checks the Task in the TaskList as done.
     *
     * @param ind Index of the Task in ArrayList.
     */
    public void markTaskDone(int ind) {
        Task t1 = tasks.get(ind);
        t1.markDone();
    }

    /**
     * Displays the current tasks in the TaskList.
     *
     * @throws DuckieException If list is empty.
     */
    public void displayList() throws DuckieException {
        if (tasks.size() == 0) {
            throw new DuckieNoListException();
        }

        Ui.displayListReply(tasks);
    }

    /**
     * Filters the list based on the keyword provided.
     *
     * @param keyword Keyword used to find matching tasks.
     * @return ArrayList containing all the tasks matching the keyword.
     */
    public ArrayList<Task> filterList(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task t1 : tasks) {
            String description = t1.getDescription();
            if (description.contains(keyword)) {
                filteredList.add(t1);
            }
        }
        return filteredList;
    }

    /**
     * Returns all the current Deadline tasks in TaskList.
     *
     * @return ArrayList containing all the Deadline Tasks.
     */
    public ArrayList<Deadline> getAllDeadlineTasks() {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (Task t1 : tasks) {
            if (t1.getType().equals("D")) {
                deadlineTasks.add((Deadline) t1);
            }
        }
        return deadlineTasks;
    }

    /**
     * Returns all the current Event tasks in TaskList.
     *
     * @return ArrayList containing all the Event Tasks.
     */
    public ArrayList<Event> getAllEventTasks() {
        ArrayList<Event> eventTasks = new ArrayList<>();
        for (Task t1 : tasks) {
            if (t1.getType().equals("E")) {
                eventTasks.add((Event) t1);
            }
        }
        return eventTasks;
    }
}
