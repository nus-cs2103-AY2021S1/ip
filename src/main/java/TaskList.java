/**
 * A class containing a list of tasks, with methods for adding new tasks, deleting and returning
 * tasks due on a specific date.
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current list of tasks.
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a task at the specified index.
     * @param index index to locate task
     * @return task at the given index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the current total number of tasks.
     * @return total number of tasks
     */
    public int count() {
        return this.tasks.size();
    }

    /**
     * Adds a new task to the list of tasks.
     * @param task new task to be added
     * @return true if a non-null task object is given
     */
    public boolean addTask(Task task) {
        if (task == null) {
            return false;
        }
        tasks.add(task);
        return true;
    }

    /**
     * Deletes the task at a specified index on the list.
     * @param index index to locate task
     * @return task that is removed
     * @throws ChatbotException if given index is out-of-bounds
     */
    public Task removeTask(int index) throws ChatbotException {
        Task removed;

        try {
            removed = this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("That item does not exist!");
        }

        return removed;
    }

    /**
     * Mark a task on the list as done.
     * @param index index to locate the task
     * @return task to be marked as done
     * @throws ChatbotException if given index is out-of-bounds
     */
    public Task markAsDone(int index) throws ChatbotException {
        Task taskDone;

        try {
            taskDone = getTask(index).markDone();
            this.tasks.set(index, taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("That item does not exist!");
        }

        return taskDone;
    }

    /**
     * Returns a list of tasks due on a given date.
     * @param date given date
     * @return list of tasks due
     */
    public ArrayList<Task> retrieveTasksOnDate(LocalDate date) {

        Iterator<Task> iter = this.tasks.iterator();
        ArrayList<Task> tasks = new ArrayList<>();

        while (iter.hasNext()) {
            Task tsk = iter.next();
            LocalDate taskDate = tsk.getDate();
            if (taskDate != null && taskDate.equals(date)) {
                tasks.add(tsk);
            }
        }
        return tasks;
    }

    public ArrayList<Task> find(String searchStr) {

        Iterator<Task> iter = this.tasks.iterator();
        ArrayList<Task> tasks = new ArrayList<>();

        while (iter.hasNext()) {
            Task tsk = iter.next();
            if (tsk.getDescription().contains(searchStr)) {
                tasks.add(tsk);
            }
        }

        return tasks;
    }
}
