package duke.core;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Encapsulates an ArrayList of Task objects and handles the tracking of tasks during the usage of the
 * Duke programme.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * No-argument method that prints out the current tasks in this TaskList sequentially.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task.toString());
        }
    }

    public void listSearch(String searchTerm) {
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(searchTerm)) {
                System.out.println(i + 1 + ". " + task.toString());
            }
        }
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * No-argument method that returns the ArrayList of Task objects in this TaskList.
     * This is used by a Storage object to save the task list during the termination of the Duke programme.
     * @return the ArrayList of Task objects in this TaskList
     * @see Storage
     */
    public ArrayList<Task> exportTaskList() {
        return tasks;
    }

    /**
     * This method is used to add tasks from an ArrayList of Task objects created by the Storage object
     * when it reads from saveData.txt to this TaskList object.
     * @param savedTasks an ArrayList of Task objects to be added to this TaskList object's ArrayList
     * @see Storage
     */
    public void loadTasks(ArrayList<Task> savedTasks) {
        if (!savedTasks.isEmpty()) tasks.addAll(savedTasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }
}
