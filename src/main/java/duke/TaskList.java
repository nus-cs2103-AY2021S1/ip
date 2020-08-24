package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the end of the list of tasks.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        getList().add(task);
        String strToPrint = "Got it. I've added this task:" + "\n" + task.toString() + "\n" + "Now you have " +
                list.size() + " task in the list.";
        System.out.println(strToPrint);
    }

    /**
     * Removes the task at the specified index from the list of tasks.
     *
     * @param index Index of task to be removed from list.
     */
    public void remove(int index) {
        Task taskToDelete = list.get(index);
        getList().remove(index);
        System.out.println("Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n" + "Now you have " +
                list.size() + " tasks in the list.");
    }
}
