import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a TaskList object to contain all the Tasks from an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object to contain all the current Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Return the size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Return the Task at the given index.
     *
     * @param index Index of the Task.
     * @return The Task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Add a Task to the list.
     *
     * @param task Task to be added.
     * @return true when Task is added.
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    /**
     * Remove the Task at the given index.
     *
     * @param index Index of the Task.
     * @return The Task that was removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> find(String word) {
        ArrayList<Task> tasksWithWord = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(word)) {
                tasksWithWord.add(task);
            }
        }
        return tasksWithWord;
    }
}
