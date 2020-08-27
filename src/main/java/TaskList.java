import java.util.ArrayList;

/**
 * Represents the list of tasks the user has and keeps track of the current state of the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates a TaskList object with a list of tasks.
     *
     * @param tasks Arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Instantiates a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task with the specified task number.
     *
     * @param taskNum The task number to be retrieved.
     * @return Task at that task number.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Adds the specified task into the list of tasks.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNum The task number to be deleted.
     */
    public void deleteTask(int taskNum) {
        this.tasks.remove(taskNum - 1);
    }

    /**
     * Prints the list of tasks in a numbered format.
     */
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
    }

    /**
     * Finds the matching tasks in the list according to the specified keyword.
     *
     * @param keyword The keyword to find tasks.
     * @return TaskList containing tasks with the same keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t.toString().contains(keyword)) {
                foundTasks.add(t);
            }
        }

        return new TaskList(foundTasks);
    }
}
