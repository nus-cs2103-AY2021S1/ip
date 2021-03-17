import java.util.ArrayList;

import tasks.Task;
/**
 * An object which contains the list of tasks a user needs to do
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialized the arraylist with a list of tasks
     *
     * @param tasks The list of tasks provided
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        assert this.tasks != null : "The arraylist of tasks can never be null";
        return this.tasks;
    }

    /**
     * Add a task to the tasklist
     * @param task to be added into the tasklist
     */
    public void update(Task task) {
        assert task != null : "A null task was passed in";
        tasks.add(task);
    }

    /**
     * Get a task from the given index in the tasklist
     * @param i index to be pulled
     * @return Task object
     */
    public Task get(int i) {
        assert this.tasks != null : "The arraylist of tasks can never be null";
        return tasks.get(i - 1);
    }

    /**
     * Delete at the given index
     * @param i index to be deleted
     */
    public void delete(int i) {
        assert this.tasks != null : "The arraylist of tasks can never be null";
        tasks.remove(i - 1);
    }

    /**
     * Update the task to show done
     * @param i index to be updated
     */
    public void updateStatus(int i) {
        assert this.tasks != null : "The arraylist of tasks can never be null";
        tasks.get(i - 1).updateStatus();
    }

    public int getSize() {
        assert this.tasks != null : "The arraylist of tasks can never be null";
        return tasks.size();
    }

    /**
     * The tasklist has to be converted into a String format that is easily parsable when the file is reopened
     *
     * @return String in a format that easily parsable upon reopening the file
     */
    public String save() {
        StringBuilder line = new StringBuilder();
        assert this.tasks != null : "The arraylist of tasks can never be null";
        for (Task task : tasks) {
            String append = "";
            boolean istodo = task.istodo();
            if (istodo) {
                append = task.description() + task.getWork() + "\n";
            } else {
                append = task.description() + task.getWork() + "|" + task.getDate() + "\n";
            }
            line.append(append);
        }
        return line.toString();
    }

    /**
     * Overriding the default toString method
     *
     * @return String which prints out the taskList
     */
    public String toString() {
        StringBuilder line = new StringBuilder();
        assert this.tasks != null : "The arraylist of tasks can never be null";
        for (Task task : tasks) {
            line.append(task.toString());
            line.append('\n');
        }
        return line.toString();
    }
}
