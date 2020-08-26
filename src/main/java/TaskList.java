import java.util.ArrayList;

/**
 * Stores tasks and supports various operations on them.
 */
public class TaskList implements java.io.Serializable {

    private ArrayList<Task> tasks;
    String loadMessage = "";

    /**
     * Initializes a new task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        UI.print("added: " + task.toString() + numTasks());
        Storage.store(this);
    }

    /**
     * @return a string describing the number of tasks
     */
    public String numTasks() {
        int size = tasks.size();
        return "You now have " + size + " task" + (size > 1 ? "s" : "") + " in the list.\n";
    }

    /**
     * Prints the list of tasks through the UI class
     */
    public void print_tasks() {
        System.out.print(UI.LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + "." + tasks.get(i));
        }
        System.out.print(UI.LINE);
    }

    /**
     * @param i Index of the task to be returned.
     * @return The task at index I.
     * @throws DukeException
     */
    public Task get(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        return tasks.get(i);
    }

    /**
     * @param i Index of the task to be removed.
     * @throws DukeException
     */
    public void remove(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.remove(i);
        Storage.store(this);
    }

    /**
     * @param i the index of the task to be set.
     * @param value Doneness of the task to be set.
     * @throws DukeException
     */
    public void setDone(int i, boolean value) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.get(i).done = value;
        Storage.store(this);
    }

}