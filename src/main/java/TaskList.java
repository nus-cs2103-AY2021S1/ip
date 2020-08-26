import java.util.ArrayList;

/**
 * A TaskList object stores tasks input by the user in a list.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return Number of tasks in the list.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * @param i Task number.
     * @return Task object with task number i.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * @param i Remove Task object with task number i from the list.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * @param t Task to be added to the list.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * @return String representing all tasks in the list.
     */
    public String displayTasks() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
