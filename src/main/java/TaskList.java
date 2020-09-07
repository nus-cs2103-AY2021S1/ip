import java.util.ArrayList;

/**
 * Represents the task list that stores all current tasks.
 */
public class TaskList {
    /**
     * The list of task.
     */
    private static ArrayList<Task> tasks;
    /**
     * The user interface.
     */
    private static Ui ui;

    /**
     * Creates a new <code>TaskList</code> with the given arraylist that contains tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Creates an empty <code>TaskList</code>.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Returns the task list.
     * @return list of this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a <code>task</code> to this <code>TaskList</code>.
     * @param task The task to be added.
     */
    public static void add(Task task) {
        tasks.add(task);
        ui.add(task, tasks);
    }

    /**
     * Marks a task in this list as done.
     * @param n Index of the task that is done.
     */
    public static void done(int n) {
        tasks.get(n-1).setDone();
        ui.done(n, tasks);
    }

    /**
     * Deletes a task in this list.
     * @param n Index of the task to be deleted.
     */
    public static void delete(int n) {
        ui.delete(n, tasks);
        tasks.remove(n-1);
        ui.count(tasks);
    }

    /**
     * Finds tasks that matches the given keyword.
     * @param keyword The given keyword.
     */
    public void find(String keyword) {
        ArrayList<Task> results = new ArrayList<Task>();
        for (Task task: tasks) {
            if (task.getCommand().contains(keyword)) {
                results.add(task);
            }
        }
        ui.findKeyword(results);
    }
}
