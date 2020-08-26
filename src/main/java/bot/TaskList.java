package bot;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds newtask to list.
     * @param newTask
     */
    public void add(Task newTask) {
        this.list.add(newTask);
    }

    /**
     * Returns the Task at userIndex - 1.
     * If no Task is found, throw IllegalArgumentException
     * @param userIndex
     * @return
     * @throws IllegalArgumentException
     */
    public Task get(int userIndex) throws IllegalArgumentException {
        try {
            int index = userIndex - 1;
            return list.get(index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Removes the Task at userIndex - 1.
     * @param userIndex
     * @throws IllegalArgumentException
     */
    public void remove(int userIndex) throws IllegalArgumentException {
        try {
            int index = userIndex - 1;
            this.list.remove(index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the list
     * @return ArrayList of Task
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the length of the list
     * @return length of list
     */
    public int getSize() {
        return this.list.size();
    }
}
