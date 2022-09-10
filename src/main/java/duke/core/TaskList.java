package duke.core;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates an ArrayList of Task objects and handles the tracking of tasks during the usage of the
 * Duke programme.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * No-argument method that returns out the current tasks in this TaskList sequentially as a String.
     * @return a string including newline characters that depict the tasks in this TaskList.
     */
    public String list() {
        assert tasks != null;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            buffer.append(i + 1 + ". " + task.toString() + "\n");
        }
        return buffer.toString();
    }

    /**
     * Searches the TaskList for all tasks with descriptions containing the search term and prints them out in order.
     * @param searchTerm the string to be searched for within all the task descriptions
     */
    public String listSearch(String searchTerm) {
        assert tasks != null;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(searchTerm.trim().toLowerCase())) {
                buffer.append(i + 1 + ". " + task.toString() + "\n");
            }
        }
        return buffer.toString();
    }

    public Task get(int i) {
        assert tasks != null;
        return tasks.get(i);
    }

    /**
     * No-argument method that returns the ArrayList of Task objects in this TaskList.
     * This is used by a Storage object to save the task list during the termination of the Duke programme.
     * @return the ArrayList of Task objects in this TaskList
     * @see Storage
     */
    public ArrayList<Task> exportTaskList() {
        assert tasks != null;
        return tasks;
    }

    /**
     * This method is used to add tasks from an ArrayList of Task objects created by the Storage object
     * when it reads from saveData.txt to this TaskList object.
     * @param savedTasks an ArrayList of Task objects to be added to this TaskList object's ArrayList
     * @see Storage
     */
    public void loadTasks(ArrayList<Task> savedTasks) {
        assert tasks != null;
        if (!savedTasks.isEmpty()) {
            tasks.addAll(savedTasks);
        }
    }

    public void addTask(Task task) {
        assert tasks != null;
        tasks.add(task);
    }

    public void deleteTask(int index) {
        assert tasks.size() > 0;
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }
}
