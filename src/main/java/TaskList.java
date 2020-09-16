import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Add a new task.
     * @param t
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Delete the specified task.
     * @param i
     */
    public void delete(int i) {
        list.remove(i - 1);
    }

    /**
     * Update the status of the task at a particular position.
     * @param i
     * @param newTask
     */
    public void setTask(int i, Task newTask) {
        this.list.set(i - 1, newTask);
    }

    /**
     * Generate a string with the task size.
     * @return a String indicating the task size.
     */
    public String printTaskSize() {
        return ("Now you have " + this.list.size() +
                (this.list.size() > 1 ? " tasks" : " task") + " on the list");
    }
}
