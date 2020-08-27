package duke.component;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String INDENT = "  ";
    Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye!!! Hope to see you again real soon.");
    }

    public void showDone() {
        System.out.println("The following task has been marked done: ");
    }

    public void showLine() {
        System.out.println("_____________________________________________________");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * prints out all the tasks.
     * @param list list of tasks.
     */
    public void listAllTasks(ArrayList<Task> list) {
        int LENGTH_OF_LIST = list.size();
        if (LENGTH_OF_LIST > 0) {
            int counter = 1;
            for (Task task : list) {
                System.out.println("  " + counter + "." + task);
                counter++;
            }
        } else {
            System.out.println("No tasks found, add a task now!");
        }
    }

    /**
     * message after adding a task to list.
     * @param task task to be added.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     */
    public void addMessage(Task task, int tasksLeft) {
        System.out.println("Got it, the following task has been added:\n" + INDENT + INDENT + task +
                "\n" + INDENT + this.taskLeftMessage(tasksLeft));
    }

    /**
     * message after searching for term with Find
     * @param listOfTasksFound list of taks found containing that term
     */
    public void findMessage(ArrayList<Task> listOfTasksFound) {
        if (listOfTasksFound.isEmpty()) {
            System.out.println("No tasks found with that term");
        } else {

            System.out.println("Here are the matching tasks in your list: \n");

            for (int i = 1; i <= listOfTasksFound.size(); i++) {
                System.out.println(i + "." + listOfTasksFound.get(i - 1));
            }
        }
    }

    /**
     * message after deleting a message.
     * @param task task to be deleted.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     */
    public void deleteMessage(Task task, int tasksLeft) {
        System.out.println("Noted. I have removed this task:\n" + INDENT + INDENT + task + "\n" + this.taskLeftMessage(tasksLeft));
    }

    /**
     * message to show number of tasks left.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     * @return string of message.
     */
    public String taskLeftMessage(int tasksLeft) {
        String end = "No tasks found. Add a task now!";
        if (tasksLeft > 0) {
            end = "Now you have " + tasksLeft + " tasks in the list.";
        }
        return end;
    }

    public void print(String s) {
        System.out.println(s);
    }

    /**
     * shows icon based on whether task is done or not.
     * @param task task to be checked.
     * @return string of icon with brackets.
     */
    public String getStatusIcon(Task task) {
        return ("[" + (task.getIsDone() ? "\u2713" : "\u2718") + "] ");
    }

    public String readCommand() {
        return scan.nextLine();
    }
}
