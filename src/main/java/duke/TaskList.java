package duke;

import java.util.ArrayList;

/**
 * Represents the user's list of tasks.
 * Contains basic operations to manipulate tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Creates a copy of the user's TaskList.
     *
     * @return Clone of user's TaskList.
     */
    public ArrayList<Task> clone() {
        ArrayList<Task> tasksClone = new ArrayList<>();
        for (Task task : tasks) {
            tasksClone.add(task);
        }
        return tasksClone;
    }

    /**
     * Searches the task list for tasks that contain the input string.
     *
     * @param word String to match
     * @return Array List containing tasks taht contain the input string.
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(word)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
