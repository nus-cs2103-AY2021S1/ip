package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Contains the task list and handles any operations regarding the task list.
 */
public class TaskListHandler {
    protected ArrayList<Task> taskList;

    /**
     * Stores tasks from save file or empty task list if save file unavailable.
     *
     * @param list Task list.
     */
    public TaskListHandler(ArrayList<Task> list) {
        this.taskList = list;
    }

    /**
     * Retrieves the task list.
     *
     * @return Task list
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds specified task to the task list.
     *
     * @param task Task
     */
    public void addToList(Task task) {
        taskList.add(task);
    }

    /**
     * Removes all tasks from task list.
     *
     * @return An empty task list.
     */
    public ArrayList<Task> clearList() {
        this.taskList = new ArrayList<>();
        System.out.println("The list of tasks has been cleared.");
        return taskList;
    }

    /**
     * Prints the list and the number of items in the list.
     *
     * @throws DukeException If task list is currently empty.
     */
    public void printList() throws DukeException {
        if (taskList.isEmpty()) {
            // Asks user for tasks when printing empty list
            throw new DukeException("\u2639 Oops, the list of tasks is empty, pls add tasks first");
        }
        int listPos = 1;
        indent(1);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++,listPos++) {
            indent(2);
            System.out.println(listPos + ". " + taskList.get(i));
        }
        indent(1);
        System.out.println("You have " + taskList.size() + " task(s) in the list");
    }

    /**
     * Provides indentation for formatting.
     *
     * @param times The number of 4 spaces to be printed.
     */
    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }
}
