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

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

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
