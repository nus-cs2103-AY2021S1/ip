import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Initialises a TaskList with an empty ArrayList<Task>.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * returns task at the given index.
     * @param index index of task.
     * @return task at given index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns number of tasks.
     * @return int representing size of ArrayList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Add task to TaskList
     * @param t task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Removes task at selected index.
     * @param index index of task in ArrayList.
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
