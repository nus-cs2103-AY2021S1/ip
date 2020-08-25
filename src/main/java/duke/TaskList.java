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
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Task task : list) {
            if (task == null) {
                break;
            } else {
                output.append(counter).append(". ").append(task.toString()).append("\n");
                counter++;
            }
        }
        return output.toString();
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

    /**
     * Filters through list of Tasks for appropriate keyword within instructions
     * @param keyword Word to filter by
     * @return A String of filtered Lists of Tasks
     */
    public String filter(String keyword) {
        List<Task> filteredList = new ArrayList<>();
        String loweredCaseKeyword = keyword.toLowerCase();
        for (Task task : this.list ) {
            if (task.instructions.toLowerCase().contains(loweredCaseKeyword)) {
                filteredList.add(task);
            }
        }
        return iterateFilteredList(filteredList);
    }

    /**
     * Helper function to iterate through filtered list
     * @param list A list of filtered Tasks
     * @return A String format of list of filtered Tasks
     */
    public String iterateFilteredList(List<Task> list) {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Task task : list) {
            output.append(counter).append(". ").append(task.toString()).append("\n");
            counter++;
        }
        return output.toString();
    }
}
