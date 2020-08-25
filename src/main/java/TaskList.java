import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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
            String[] st = s.split(" \\| ");
            if (st[0].equals("T")) {
                ToDo t = new ToDo(st[2]);
                if (st[1].equals("1")) {
                    t.markAsDone();
                }
                temp.add(t);
            } else if (st[0].equals("D")) {
                Deadline d = new Deadline(st[2], st[3]);
                if (st[1].equals("1")) {
                    d.markAsDone();
                }
                temp.add(d);
            } else {
                Event e = new Event(st[2], st[3]);
                if (st[1].equals("1")) {
                    e.markAsDone();
                }
                temp.add(e);
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
        for (Task t : tasks) {
            lst.add(t.toString());
        }
        ui.showList(lst);
    }

    /**
     * Marks a specified task as done and alerts the user interface of the
     * Duke application to display the corresponding message.
     * @param i the position of the task to be marked as done in the task list.
     * @param ui the user interface of the Duke application.
     */
    public void markDone(int i, Ui ui) {
        tasks.get(i).markAsDone();
        ui.showDone(tasks.get(i));
    }

    /**
     * Removes a specified task from the task list and alerts the user interface
     * of the Duke application to display the corresponding message.
     * @param i the position of the task to be removed in the task list.
     * @param ui the user interface of the Duke application.
     */
    public void deleteTask(int i, Ui ui) {
        Task t = tasks.remove(i);
        ui.showDelete(t, tasks.size());
    }

    /**
     * Adds a new task to the task list and alerts the user interface of the
     * Duke application to display the corresponding message.
     * @param t the new task to be added to the task list.
     * @param ui the user interace of the Duke application.
     */
    public void addTask(Task t, Ui ui) {
        tasks.add(t);
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
