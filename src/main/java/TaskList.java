import java.util.ArrayList;

/**
 * Class that helps store the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object that contains an arraylist of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks that is stored in the TaskList object.
     *
     * @return ArrayList list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task into the list.
     *
     * @param task Current task that is to be added into the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param value Index of the task to be marked done.
     * @throws IndexOutOfBoundsException If the index that is provided is not valid.
     */
    public String markAsDone(int value) throws IndexOutOfBoundsException {
        String message = "";
        try {
            message = this.tasks.get(value).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
        return message;
    }

    /**
     * Deletes a task.
     *
     * @param value Index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index that is provided is not valid.
     */
    public void deleteTask(int value) throws IndexOutOfBoundsException {
        try {
            this.tasks.remove(value);
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return int Number of tasks in the list.
     */
    public int numOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns a task with the respective index.
     *
     * @param value Index of the task to be obtained.
     * @return Task Task to be obtained with the index.
     */
    public Task getTask(int value) {
        return this.tasks.get(value);
    }
}
