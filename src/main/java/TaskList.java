import java.util.ArrayList;
import java.util.List;

/**
 * Represents the task list of all tasks in Duke.
 */
public class TaskList {
    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Delete a task to task list.
     * @param taskNumber The task number representing task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Add a task to task list.
     * @param task The task number representing task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task); // try replacing without an index, should work also
    }

    /**
     * Mark a task in task list as done.
     * @param taskNumber The task number representing task to be marked as done in task list.
     */
    public void doneTask(int taskNumber) {
        tasks.set(taskNumber - 1, tasks.get(taskNumber - 1).markAsDone());
    }

}
