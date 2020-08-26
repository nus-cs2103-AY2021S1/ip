package duke;

import java.util.ArrayList;

/**
 * Represents taskList containing tasks.
 */
public class TaskList {

    /** ArrayList of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new instance of TaskList with an arraylist of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds tasks to TaskList.
     *
     * @param task ArrayList of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns an Arraylist of tasks with keyword.
     *
     * @param userInput User input as string.
     * @return Arraylist of tasks with keyword.
     */
    public ArrayList<Task> findTasks(String userInput) {
        String keyword = userInput.substring(5);
        ArrayList<Task> findings = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTask().contains(keyword)) {
                findings.add(task);
            }
        }
        return findings;
    }

    /**
     * Deletes task from task list at this index.
     *
     * @param index Index at which task should be removed at.
     * @return Task to be removed.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns arraylist of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
