import java.util.ArrayList;

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

    public void markAsDone(int idx) throws DukeException, TaskException {
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
        assert task == null : "Task should not be null";
        tasks.add(task);
    }

    public int getCount() {
        return tasks.size();
    }

}
