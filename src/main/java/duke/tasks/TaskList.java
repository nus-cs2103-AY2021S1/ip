package duke.tasks;
import java.util.ArrayList;

/**
 * A list of Tasks that has several methods to manipulate the Tasks.
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * Constructor.
     * @param taskList ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Empty Constructor to set up new list.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Gets the size of the Task list.
     * @return integer size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds a new task
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Set a current Task in the TaskList to another replacement task.
     * @param index
     * @param replacementTask
     */
    public void set(int index, Task replacementTask) {
        this.taskList.set(index, replacementTask);
    }

    /**
     * Get the Task in TaskList that corresponds a given index.
     * @param index index in the TaskList.
     * @return Task.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Remove a Task in TaskList that corresponds to a given index.
     * @param index in the TaskList.
     * @return the removed Task.
     */
    public Task remove(int index) {
        Task deletedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return deletedTask;
    }

    /**
     * Print the outputs of the Tasks in the TaskList.
     */
    public String print() {
        String string;
        string = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String stringCount = String.valueOf(i + 1);
            string += stringCount + ". " + taskList.get(i) + "\n";
        }
        return string;
    }

    /**
     * Set the Task in TaskList corresponding to a given index as done.
     * @param index in the TaskList.
     * @return the completed Task.
     */
    public Task setDoneTask(int index) {
        Task task = this.taskList.get(index);
        task.markDone();
        this.taskList.set(index, task);
        return task;
    }

}
