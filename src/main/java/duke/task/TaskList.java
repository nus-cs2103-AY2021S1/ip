package duke.task;

import java.util.ArrayList;

/**
 * The <code>ArrayList</code> containing all the Tasks that Duke is storing.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor to create a new TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor, used when there are existing tasks being loaded from the user's local storage.
     *
     * @param tasks is the List of Tasks that is created from loading the Storage's tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param newTask the task to be added.
     */
    public void add(Task newTask) {
        this.taskList.add(newTask);

    }

    /**
     * Removes the specified task from the TaskList.
     *
     * @param index the index of the Task to be deleted from the TaskList.
     * @return the Task that has been deleted.
     */
    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    /**
     * The number of Tasks in the TaskList.
     *
     * @return an <code>Integer</code> representing the number of Tasks in the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves a Task from the TaskList at a specified index.
     *
     * @param index the Task in the TaskList to be retrieved.
     * @return the Task that is retrieved.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Prints out all the Tasks in the TaskList that contains the keyword specified by the user.
     *
     * @param keyword the key to filter the Tasks in the TaskList by.
     * @return String representing all the filtered items in the TaskList.
     */
    public String showSpecifiedItems(String keyword) {
        TaskList filteredTasks = new TaskList();

        ArrayList<Task> currList = this.taskList;
        currList.forEach(item -> {
            if (item.description.contains(keyword)) {
                filteredTasks.add(item);
            }
        });

        return filteredTasks.toString();
    }

    /**
     * Returns a string representation of the Tasks in the list.
     *
     * @return a string of all the tasks in the list.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Here are all the items in your list:\n");

        for (int i = 0; i < this.taskList.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }

        return builder.toString().trim();
    }
}
