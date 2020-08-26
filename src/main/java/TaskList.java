import java.util.ArrayList;

/**
 * Represents a list of all tasks.
 */
public class TaskList {

    private ArrayList<Task> tasksList;
    private int totalTasks;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        this.totalTasks = tasksList.size();
    }

    public TaskList() {
        this.tasksList = new ArrayList<>();
        this.totalTasks = 0;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task task to be added.
     */
    public void add(Task task) {
        tasksList.add(task);
        totalTasks++;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskNumber number of task to be deleted.
     */
    public void delete(int taskNumber) {
        tasksList.remove(taskNumber - 1);
        totalTasks--;
    }

    /**
     * Marks a task as done.
     * @param taskNumber number of task to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) {
        tasksList.get(taskNumber - 1).markAsDone();
    }

    /**
     * Returns a specific task.
     * @param taskNumber number of task to be returned.
     * @return a specific task.
     */
    public Task getTask(int taskNumber) {
        return tasksList.get(taskNumber - 1);
    }

    /**
     * Returns the total number of tasks.
     * @return total number of tasks.
     */
    public int getTotalTasks() {
        return this.totalTasks;
    }

    /**
     * Returns the list of tasks.
     * @return list of tasks.
     */
    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    /**
     * Lists all tasks.
     */
    public void listTasks() {
        for (int i = 1; i <= totalTasks; i++) {
            System.out.println(i + "." + tasksList.get(i - 1));
        }
    }
}
