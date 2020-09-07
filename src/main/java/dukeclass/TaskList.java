package dukeclass;

import java.util.ArrayList;

/**
 * Represents a list of Tasks using an Arraylist
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Replaces the Task at the specified position in this list with the specified Task.
     *
     * @param index index of the Task to replace.
     * @param newTask Task to be stored at the specified position
     *
     * @return the Task previously at the specified position.
     */
    public Task set(int index, Task newTask) {
        return this.taskList.set(index, newTask);
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    public String toString() {
        String finalString = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            finalString += this.taskList.get(i).toString() + "\n";
        }
        return finalString;
    }


}
