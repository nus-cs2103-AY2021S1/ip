import java.util.ArrayList;

/**
 * Responsible for storing a list of Tasks and interaction with the Tasks.
 * A <code>TaskList</code> contains an <code>ArrayList</code> object.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    @Override
    public String toString() {
        String listString = "";
        for (Task task : tasks) {
            listString += task + "\n";
        }
        return listString;
    }

    /**
     * Prints the list of tasks in order.
     *
     * @param ui A Ui object.
     */
    public void printList(Ui ui) {
        if (tasks.size() == 0) ui.say("Your task list is currently empty.");
        else {
            ui.say("Here is your task list.");
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    /**
     * Marks the (number - 1)th task in the list as done.
     *
     * @param number Task number.
     * @param ui A Ui object.
     */
    public void markTaskDone(int number, Ui ui) {
        Task task = tasks.get(number - 1);
        task.markDone();
        ui.say("I have marked it as done!");
        System.out.println(task);
    }

    /**
     * Deletes the (number - 1)th task in the list.
     *
     * @param number Task number.
     * @param ui A Ui object.
     */
    public void deleteTask(int number, Ui ui) {
        Task task = tasks.get(number - 1);
        tasks.remove(number - 1);
        ui.say("I have deleted this task!");
        System.out.println(task);
        ui.say("You have " + getListSize() + " items in your task list now.");
    }

    /**
     * Adds a task to the back of the list.
     *
     * @param task The Task to be added.
     * @param ui A Ui object.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.say("You have " + getListSize() + " items in your task list now.");
    }

    /**
     * Finds a task that matches the String body parameter.
     *
     * @param body String to search for.
     * @param ui A Ui object.
     */
    public void findTask(String body, Ui ui) {
        if (tasks.size() > 0) {
            ui.say("Here are the matching tasks in your list:");
            boolean noneFound = true;
            for (Task task : tasks) {
                if (task.toString().contains(body)) {
                    System.out.println(task);
                    noneFound = false;
                }
            }
            if (noneFound) ui.say("No matching tasks.");
        } else {
            ui.say("You have no tasks yet.");
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return The list size.
     */
    public int getListSize() {
        return tasks.size();
    }
}