import java.util.ArrayList;

/**
 * Encapsulates the TaskList containing the tasks.
 * @param <Task> the Task objects
 */
public class TaskList<Task> {
    ArrayList<Task> taskList;

    /**
     * Instantiates new TaskList object to store Tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Instantiates TaskList Object to store existing Tasks.
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Check if TaskList is empty.
     * @return if taskList is empty or not
     */
    boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Checks TaskList size.
     * @return the size of the TaskList
     */
    int size() {
        return taskList.size();
    }

    /**
     * Gets a task at the given position.
     * @param position the position of the task
     * @return the task at the given position
     */
    Task get(int position) {
        return taskList.get(position);
    }

    /**
     * Replaces a new Task at given position in line with immutability.
     * @param position the position of the task
     * @param newTask the new task to input
     */
    void set(int position, Task newTask) {
        taskList.set(position, newTask);
    }

    /**
     * Adds a Task to TaskList.
     * @param taskToDo the task to be added
     */
    void add(Task taskToDo) {
        taskList.add(taskToDo);
    }

    /**
     * Removes a Task from the TaskList.
     * @param taskToRemove the task to be removed
     */
    void remove(Task taskToRemove) {
        taskList.remove(taskToRemove);
    }

    /**
     * Exports the TaskList as an arrayList for easier processing.
     * @return the arrayList containing the Tasks
     */
    ArrayList<Task> exportList() {
        return taskList;
    }
}
