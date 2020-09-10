import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a new task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Counts the number of tasks in the list.
     * @return number of tasks
     */
    public int count() {
        return tasks.size();
    }

    /**
     * Returns the task at the specific position.
     * @param index the position of the task
     * @return the specified task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * add a task to the list.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a task from the list.
     * @param number the index of the task to be removed
     */
    public void deleteTask(int number) {
        tasks.remove(number);
    }

    /**
     * Finds all of the tasks that contain the keyword.
     * @param word the keyword
     * @return a list of matching tasks
     */
    public TaskList findTasks(String word) {
        TaskList matchingTasks = new TaskList();

        for(Task task: tasks) {
            if(task.getDescription().contains(word)) {
                matchingTasks.addTask(task);
            }
        }

        return matchingTasks;
    }
}
