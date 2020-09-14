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
     * Returns task at the given index.
     *
     * @param index Index of task.
     * @return Task at given index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns number of tasks.
     *
     * @return Int representing size of ArrayList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Adds task to TaskList
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Removes task at selected index.
     *
     * @param index Index of task in ArrayList.
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    public void update(int index, Task updated) {
        boolean isDone = this.list.get(index).isDone;
        this.list.remove(index);
        updated.isDone = isDone;
        this.list.add(updated);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

}
