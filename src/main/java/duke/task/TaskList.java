package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor that initializes the Tasklist with a list of tasks
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the end of the list
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task at the given index (0-based)
     *
     * @param index index of the task to be deleted (0-based)
     */
    public void delete(int index) {
        list.remove(index);
    }

    /**
     * Checks if the TaskList is empty or not
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Get the task at a specified index
     *
     * @param index index of the task to be returned (0-based)
     * @return the task at the specified index (0-based)
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Returns the current size of the TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Getter for underlying List of Tasks
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Returns filtered tasks based on exact matching with keyword
     *
     * @return a list of tasks that satisfy the constraints
     */
    public TaskList filter(String keyword) {
        List<Task> filteredList = new ArrayList<>();
        for(Task task: list) {
            if(task.toString().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return new TaskList(filteredList);
    }

    /**
     * Returns itemized tasks, each with serial numbers (1-based)
     *
     * @return a list of strings of the form "1. deadline sample task /by time"
     */
    public List<String> itemize() {
        List<String> itemizedList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            itemizedList.add((i + 1) + ". " + list.get(i));
        }
        return itemizedList;
    }

    /**
     * Returns itemized tasks with an intro message, each with serial numbers (1-based)
     *
     * @param introText the text to be displayed before the itemized list
     * @return a list of strings of the form "1. deadline sample task /by time"
     */
    public List<String> itemize(String introText) {
        List<String> itemizedList = new ArrayList<>();
        itemizedList.add(introText);
        for(int i = 0; i < list.size(); i++) {
            itemizedList.add((i + 1) + ". " + list.get(i));
        }
        return itemizedList;
    }
}
