/**
 * A class representing the command line level user interface. Responsible for reading commands
 * from the user and displaying data, success/error messages to the user.
 */

package ui;

import data.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void display(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + message);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints out a welcome message.
     */
    public void greet() {
        System.out.println(
                "   ####################################################\n" +
                        "   #                                                  #\n" +
                        "   #  Hey there, I'm Hanry Kun the impatient ChatBot. #\n" +
                        "   #  What can I do for you? (-.-)                    #\n" +
                        "   #                                                  #\n" +
                        "   ####################################################"
        );
    }

    /**
     * Reads in the command given by the user.
     * @return the user command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out a success message for adding a task.
     * @param task task added
     * @param count current number of tasks in the task list
     */
    public void addSuccess(Task task, int count) {
        display("Got it. I've added this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count));
    }

    /**
     * Prints out a success message for deleting a task.
     * @param task task deleted
     * @param count current number of tasks in the task list
     */
    public void deleteSuccess(Task task, int count) {
        display("Alright. I've removed this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count));
    }

    /**
     * Prints out a success message for marking a task as done.
     * @param task task marked as done
     */
    public void markDoneSuccess(Task task) {
        display("Nice! I've marked this task as done:\n    " +
                "    " + task);
    }

    /**
     * Prints out the given error message.
     * @param message the error message
     */
    public void showErrorMessage(String message) {
        display(message);
    }

    /**
     * List out the given tasks.
     * @param ls list of tasks
     */
    public void list(ArrayList<Task> ls) {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    ######################## Your Tasks ########################");
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        if (ls.size() == 0 ) {
            System.out.println("    No task here.");
            return;
        }

        Iterator<Task> iter = ls.iterator();
        int index = 1;

        while (iter.hasNext()) {
            System.out.println("    " + index + ". " + iter.next());
            index++;
        }

        System.out.println("    - End of list -");
    }
}
