package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList object stores the List of Tasks
 */
public class TaskList {
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getList() {
        return this.list;
    }

    /**
     * Iterates through the list of Tasks stored
     * @return A String of List of Tasks stored
     */
    public String iterateToDo() {
        String output = "";
        int counter = 1;
        for (Task task : list) {
            if (task == null) {
                break;
            } else {
                output += Integer.toString(counter) + ". " + task.toString() + "\n";
                counter++;
            }
        }
        return output;
    }

    /**
     * Removes a specified Task from the list of Tasks
     * @param number Position of Task to delete
     */
    public void deleteTask(int number) {
        this.list.remove(number - 1);
    }

    /**
     * Adds a specified Task to the list of Tasks
     * @param counter Position to add Task
     * @param task Specified Task to be added
     */
    public void addTask(int counter, Task task) {
        this.list.add(counter, task);
    }
}
