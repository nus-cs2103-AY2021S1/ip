package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class contains the task list and has operations
 * to modify the list.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor for a TaskList object with a new list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList object with an existing list.
     *
     * @param list An arraylist which is used to store Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list in the TaskList object.
     *
     * @return An arraylist containing all the Tasks stored in the TaskList.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a new Task to the list.
     *
     * @param task A Task which contains the name and details of a task.
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (list.size() > 1) {
            System.out.println("Now you have " + list.size() + " tasks in your list");
        } else {
            System.out.println("Now you have " + list.size() + " task in your list");
        }
    }

    /**
     * Modifies a specific task stored in the list using the done()
     * method of the Task object.
     *
     * @param number The number of the task to be marked as done.
     */
    public void markAsDone(int number) {
        list.get(number).done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(number));
    }

    /**
     * Removes a specific task stored in the list.
     *
     * @param number The number of the task to be deleted from the list.
     */
    public void deleteTask(int number) {
        Task deletedTask = list.get(number);
        list.remove(number);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("You now have " + list.size() + " tasks in your list");
    }
}