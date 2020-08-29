package duke.classes;

import java.util.List;

import duke.tasks.Task;

/**
 * Class that handles the interactions between chatbot and users.
 */
public class Ui {

    protected String line = "---------------------------------------------------";

    /**
     * Default constructor with welcome message.
     */

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = line + "\nHello! I'm Duke!\n"
                + "What can I do for you?\n";
        System.out.println(welcome);
    }

    /**
     * Iterates the todolist and prints out its objects.
     * @param list Todo List
     */

    public void displayList(List<Task> list) {
        System.out.println(line);
        if (list.size() == 0) {
            System.out.println("Sorry. No matches found!");
        } else {
            for (Task task : list) {
                System.out.println(task.getStatusWithIndex());
            }
        }
    }

    /**
     * Prints out the text after addition of the main types of tasks.
     * @param task Task added
     * @param list Todo List used for storage
     */

    public void addTask(Task task, List<Task> list) {
        System.out.println(task == null
                ? "Failed!"
                : line + "\nGot it. I've added this task:\n   " + task.toString()
                    + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Prints out the text after addition of a normal task.
     * @param task Task added
     */

    public void addOtherTask(String task) {
        System.out.println(line + "\nadded: " + task);
    }

    /**
     * Prints out the status of a task once it is checked as complete.
     * @param task Task completed
     */

    public void completeTask(Task task) {
        System.out.println(line + "\nNice! I have marked this task as done: \n  "
                + task.toString());
    }

    /**
     * Deletes a task from the current todolist.
     * @param task Task to be removed
     * @param list Todo List to remove tasks from
     */

    public void deleteTask(Task task, List<Task> list) {
        System.out.println(line + "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Prints out the complete message after ending program successfully.
     */

    public void endDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    /**
     * Prints out all error messages from exceptions.
     * @param error Error message derived from exceptions
     */
    public void printError(String error) {
        System.out.println(error);
    }

}
