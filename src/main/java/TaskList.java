import java.util.ArrayList;
import java.util.Date;

/**
 * TaskList contains all the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int idx) throws DukeException {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("task index out of bounds");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markAsDone(int idx) throws DukeException {
        getTask(idx).markAsDone();
    }

    /**
     * Deletes a task from tasks.
     *
     * @param idx Index in tasks for which task is to be removed.
     * @return Task to be removed.
     * @throws DukeException
     */
    public Task removeTask(int idx) throws DukeException {
        try {
            return tasks.remove(idx);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("task index out of bounds");
        }
    }

    /**
     * Adds a task to tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getCount() {
        return tasks.size();
    }

    /**
     * Overloaded toString method that returns list of indexed tasks according to provided date and keyWord.
     *
     * @param date Date to filter tasks by, if null all Tasks will pass.
     * @param keyWord Keyword to filter tasks by, if null all Tasks will pass.
     * @return String representing list of tasks that fulfil date and keyword criteria.
     */
    public ArrayList<String> toString(Date date, String keyWord) {
        ArrayList<String> lst = new ArrayList<>();
        int i = 1;
        for (Task task : tasks) {
            if (task.fulfilCriteria(date, keyWord)) {
                lst.add((i++) + ". " + task.toString());
            }
        }
        return lst;
    }
}
