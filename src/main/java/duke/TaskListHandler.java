package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Contains the task list and handles any operations regarding the task list.
 */
public class TaskListHandler {
    protected ArrayList<Task> tasks;

    /**
     * Stores tasks from save file or empty task list if save file unavailable.
     *
     * @param list Task list.
     */
    public TaskListHandler(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Retrieves the task list.
     *
     * @return Task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds specified task to the task list.
     *
     * @param task Task
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Removes all tasks from task list.
     *
     * @return An empty task list.
     */
    public ArrayList<Task> clearList() {
        this.tasks = new ArrayList<>();
        Ui.drawTopBorder();
        Ui.indent(1);
        System.out.println("The list of tasks has successfully been cleared.");
        Ui.drawBottomBorder();
        return tasks;
    }

    /**
     * Prints the list and the number of items in the list.
     *
     * @throws DukeException If task list is currently empty.
     */
    public void printList() throws DukeException {
        if (tasks.isEmpty()) {
            // Asks user for tasks when printing empty list
            throw new DukeException("\u2639 Oops, the list of tasks is empty, pls add tasks first!");
        }
        int listPos = 1;
        Ui.drawTopBorder();
        Ui.indent(1);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++, listPos++) {
            Ui.indent(2);
            System.out.println(listPos + ". " + tasks.get(i));
        }
        Ui.indent(1);
        System.out.println("You have " + tasks.size() + " task(s) in the list");
        Ui.drawBottomBorder();
    }

    /**
     * Provides indentation for formatting.
     *
     * @param times The number of 4 spaces to be printed.
     */
    public static void indent(int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("    ");
        }
    }
}
