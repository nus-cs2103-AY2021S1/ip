package duke;

import java.util.ArrayList;

import task.Task;

/**
 * A TaskList object stores tasks input by the user in a list.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return Number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * @param i task.Task number.
     * @return task.Task object with task number i.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * @param i Remove task.Task object with task number i from the list.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * @param t task.Task to be added to the list.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Searches for and returns the list of tasks containing keyword.
     *
     * @param keyword Keyword user is searching for.
     * @return ArrayList of tasks containing keyword.
     */
    public ArrayList<Task> searchFor(String keyword) {
        ArrayList<Task> searchResult = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            String currTaskName = currTask.getTaskName();
            if (currTaskName.contains(keyword)) {
                searchResult.add(currTask);
            }
        }
        return searchResult;
    }

    /**
     * @return String representing all tasks in the list.
     */
    public String displayTasks() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i == this.tasks.size() - 1) {
                output += (i + 1) + ". " + this.tasks.get(i).toString();
            } else {
                output += (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
            }
        }
        return output;
    }
}
