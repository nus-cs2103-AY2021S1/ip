package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> lines;
    private int numberOfItems;

    /**
     * The constructor for the TaskList object. It takes in an ArrayList<String> which would be manipulated using the
     * methods in the object. It also stores the number of items in said ArrayList and the number gets updated with
     * certain TaskList methods. This is to reduce the number of calls to ArrayList.size().
     *
     * @param lines The ArrayList to be stored and manipulated.
     */
    public TaskList(ArrayList<String> lines) {
        this.lines = lines;
        this.numberOfItems = lines.size();
    }

    /**
     * Adds a task to the task list if the number of items is less than 100. Updates the number of items in the list
     * accordingly.
     *
     * @param task The task to be added
     */
    public void addTask(String task) {
        if (numberOfItems < 100) {
            lines.add(task);
            numberOfItems += 1;
        }
    }

    /**
     * Removes a task from the task list based on the index provided. Updates the number of items in the list
     * accordingly.
     *
     * @param index the index of the task to be removed.
     */
    public void removeTask(int index) {
        lines.remove(index);
        numberOfItems -= 1;
    }

    /**
     * Changes a task in the task list based on the index provided to the task provided.
     *
     * @param task The updated task.
     * @param index the index of the task to be replaced.
     */
    public void updateTask(String task, int index) {
        lines.set(index, task);
    }

    /**
     * Returns the stored arrayList of tasks.
     *
     * @return the lists of tasks currently stored.
     */
    public ArrayList<String> getList() {
        return lines;
    }

    /**
     * Returns the task stored in the position provided. This method is only called after verifying that the position
     * is valid, i.e not negative and not more than the number of items in the stored list. This is done in the Parser
     * class.
     *
     * @param position the position of the task desired.
     * @return the string representing the task desired.
     */
    public String getTask(int position) {
        return lines.get(position);
    }

    /**
     * Simply returns the number of items in the stored list.
     *
     * @return the number of items in the stored list.
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Finds all tasks that contains the keyword
     *
     * @param keyword The keyword that we want our resulting tasks to have
     * @return An arrayList containing the tasks that contains the keyword
     */
    public ArrayList<String> find(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String task = lines.get(i);
            if (task.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
