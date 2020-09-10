import java.util.ArrayList;
import java.util.List;

/**
 * Represents the task list of all tasks in Duke.
 */
public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        assert tasks != null : "Tasklist is not initialised.";
        assert taskNumber > 0 && taskNumber <= tasks.size() : "Task number is invalid.";
        return tasks.get(taskNumber - 1);
    }

    public List<Task> getTaskList() {
        assert tasks != null : "Tasklist is not initialised.";
        return this.tasks;
    }

    public int getNumTasks() {
        assert tasks != null : "Tasklist is not initialised.";
        return tasks.size();
    }

    /**
     * Delete a task to task list.
     *
     * @param taskNumber The task number representing task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        assert tasks != null : "Tasklist is not initialised.";
        assert taskNumber > 0 && taskNumber <= tasks.size() : "Task number is invalid.";
        tasks.remove(taskNumber - 1);
    }

    /**
     * Add a task to task list.
     *
     * @param task The task number representing task to be added.
     */
    public void addTask(Task task) {
        assert tasks != null : "Tasklist is not initialised.";
        tasks.add(task);
    }

    /**
     * Mark a task in task list as done.
     *
     * @param taskNumber The task number representing task to be marked as done in task list.
     */
    public void doneTask(int taskNumber) {
        assert tasks != null : "Tasklist is not initialised.";
        assert taskNumber > 0 && taskNumber <= tasks.size() : "Task number is invalid.";
        tasks.set(taskNumber - 1, tasks.get(taskNumber - 1).markAsDone());
    }

}
