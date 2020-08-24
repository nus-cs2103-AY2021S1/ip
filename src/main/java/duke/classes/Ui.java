package duke.classes;

import duke.tasks.Task;

import java.util.List;

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
        String welcome = line + "\nHello! I'm Duke!\n" +
                "What can I do for you?\n";
        System.out.println(welcome);
    }

    /**
     * Method to iterate the todolist and print out its objects.
     * @param list Todo List
     */

    public void displayList(List<Task> list) {
        System.out.println(line);
        for (Task task : list) {
            System.out.println(task.getStatusWithIndex());
        }
    }

    /**
     * Method to print out the text after addition of the main types of tasks.
     * @param task Task added
     * @param list Todo List used for storage
     */

    public void addTask(Task task, List<Task> list) {
        System.out.println(task == null
                ? "Failed!"
                : line + "\nGot it. I've added this task:\n   " + task.toString()
                    + "\nNow you have " + list.size() + " tasks in the list." );
    }

    /**
     * Method to print out the text after addition of a normal task.
     * @param task Task added
     */

    public void addOtherTask(String task) {
        System.out.println(line + "\nadded: " + task);
    }

    /**
     * Method to print out the status of a task once it is checked as complete.
     * @param task Task completed
     */

    public void completeTask(Task task) {
        System.out.println(line + "\nNice! I have marked this task as done: \n  "
                + task.toString());
    }

    /**
     * Method to delete a task from the current todolist.
     * @param task Task to be removed
     * @param list Todo List to remove tasks from
     */

    public void deleteTask(Task task, List<Task> list) {
        System.out.println(line + "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Method to print out the complete message after ending program successfully.
     */

    public void endDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public void errorLoading() {
        System.out.println("FAILURE: Unable to load data from local drive.");
    }

    /**
     * Method to print out all error messages from exceptions.
     * @param error Error message derived from exceptions
     */
    public void printError(String error) {
        System.out.println(error);
    }

}
