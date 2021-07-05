import java.util.ArrayList;
import java.util.Collections;

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
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index Index of the Task.
     * @return The Task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a Task to the list.
     *
     * @param task Task to be added.
     * @return true when Task is added.
     */
    public boolean addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);
        return true;
    }

    /**
     * Removes the Task at the given index.
     *
     * @param index Index of the Task.
     * @return The Task that was removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Index of the Task.
     * @return The Task that got updated as done.
     */
    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        Collections.sort(tasks);
        return task;
    }

    /**
     * Finds all the Tasks with descriptions containing the word.
     *
     * @param word Word to find.
     * @return The lists of Tasks containing the word.
     */
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
