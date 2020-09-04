package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class handles all the interactions, including input and output, with the user.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a string representation for exiting the program.
     */
    public String printExit() {
        String s = "Bye. Hope to see you again soon!";
        return s;
    }

    /**
     * Returns a string representation of a section divider.
     */
    public String printDivider() {
        String s = "_______________________________________________________________";
        return s;
    }

    /**
     * Returns a string representation of greeting message.
     */
    public String printGreeting() {

        String logo =
                " ___        _                    \n"
                        + "|  _ \\ _   _| |  ____   _____ _____ _____  __  ____\n"
                        + "| | | |  |  |  | | / / _ \\|  __  |__  __|___   | / _ \\|  _  \\\n"
                        + "| |_| |  |_|  |   <  __/| |   | |__||__ /   /_<  __/|     /\n"
                        + "|___/ \\__,_|_|\\_\\___|| |  | |______|______|\\___||_|\\__\\ \n";

        String s = "Hello from\n" + logo + "\n"
                + "Hello! Dukenizer is back!\nWhat can I do for you\n";

        return s;

    }

    /**
     * Returns a string representation of the task list in the program.
     */
    public String printList(TaskList lst) {
        String s = lst.toString();

        if (lst.getSize() == 0) {
            s = "There are no items found in your task list!";
        }
        return s;
    }

    /**
     * Takes in user input to be processed.
     *
     * @return Returns a string representation of the user input.
     */
    public String readCommand() {
        //Take in Input
        String query = sc.nextLine();
        return query;
    }

    /**
     * Returns a string representation of any error messages.
     *
     * @param message error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a string representation of task being successfully added.
     *
     * @param lst  TaskList in the program
     * @param task Task that was added
     */
    public String printTaskAdded(TaskList lst, Task task) {
        String s = "Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + lst.getSize() + " tasks in the list.";

        return s;
    }

    /**
     * Returns a string representation of marking Task as done.
     *
     * @param task Task to be marked done.
     */
    public String printTaskDone(Task task) {
        String s = "Nice! I've marked this task as done:\n" + task.toString();
        return s;
    }

    /**
     * Returns a string representation of Task being successfully deleted.
     *
     * @param lst  TaskList in the program
     * @param task Task to be deleted
     */
    public String printTaskDeleted(TaskList lst, Task task) {
        String s = "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + lst.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Returns a matching tasks message.
     */
    public String printMatchingTasks() {
        String s = "Here are the matching tasks in your list:";
        return s;

    }

}
