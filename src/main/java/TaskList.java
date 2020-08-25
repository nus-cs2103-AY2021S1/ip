import java.util.ArrayList;

/**
 * Represents the task list of the Duke application. The task list is responsible for
 * storing and modifying the current tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> lst) {
        ArrayList<Task> temp = new ArrayList<>();
        for (String s : lst) {
            String[] arr = s.split(" \\| ");
            if (arr[0].equals("T")) {
                ToDo todo = new ToDo(arr[2]);
                if (arr[1].equals("1")) {
                    todo.markAsDone();
                }
                temp.add(todo);
            } else if (arr[0].equals("D")) {
                Deadline deadline = new Deadline(arr[2], arr[3]);
                if (arr[1].equals("1")) {
                    deadline.markAsDone();
                }
                temp.add(deadline);
            } else {
                Event event = new Event(arr[2], arr[3]);
                if (arr[1].equals("1")) {
                    event.markAsDone();
                }
                temp.add(event);
            }
        }
        this.tasks = temp;
    }

    /**
     * Returns the current size of the task list.
     * @return the current size of the task list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Returns the task list.
     * @return the task list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Creates and sends a list of String objects representing the tasks in the task list
     * to the user interface of the Duke application for display.
     * @param ui the user interface of the Duke application.
     */
    public void showList(Ui ui) {
        ArrayList<String> lst = new ArrayList<>();
        for (Task task : tasks) {
            lst.add(task.toString());
        }
        ui.showList(lst);
    }

    /**
     * Marks a specified task as done and alerts the user interface of the
     * Duke application to display the corresponding message.
     * @param pos the position of the task to be marked as done in the task list.
     * @param ui the user interface of the Duke application.
     */
    public void markDone(int pos, Ui ui) {
        tasks.get(pos).markAsDone();
        ui.showDone(tasks.get(pos));
    }

    /**
     * Removes a specified task from the task list and alerts the user interface
     * of the Duke application to display the corresponding message.
     * @param pos the position of the task to be removed in the task list.
     * @param ui the user interface of the Duke application.
     */
    public void deleteTask(int pos, Ui ui) {
        Task t = tasks.remove(pos);
        ui.showDelete(t, tasks.size());
    }

    /**
     * Adds a new task to the task list and alerts the user interface of the
     * Duke application to display the corresponding message.
     * @param task the new task to be added to the task list.
     * @param ui the user interface of the Duke application.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
