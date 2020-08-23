package duke;

import duke.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs a TaskList object with an ArrayList of Task
     * @param taskList ArrayList of Task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns ArrayList of Task found in TaskList
     * @return ArrayList of Task
     */
    public List<Task> getList() {
        return taskList;
    }

    /**
     * Appends the specified Task at the end of the ArrayList of Task found in TaskList
     * @param task Task object to be added
     * @return true when successful, false when unsuccessful
     */
    public boolean add(Task task) {
        return this.taskList.add(task);
    }

    /**
     * Removes the Task at the specific position from the ArrayList of Task found in TaskList
     * @param index index of the task to remove
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns the Task at the specified position from the ArrayList of Task found in TaskList
     * @param index index of the task to return
     * @return
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of elements from the ArrayList of Task found in TaskList
     * @return the number of elements from the ArrayList of Task found in TaskList
     */
    public int size() {
        return this.taskList.size();
    }

    public String toString() {
        return this.taskList.toString();
    }
}