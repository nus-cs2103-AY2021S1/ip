package duke.ui;

import java.util.Scanner;

import duke.task.Task;

/**
 * Contains and executes all user interaction methods.
 */
public class Ui {

    /** Takes in user commands from the command line. */
    private Scanner sc;

    /**
     * Initialises the Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /** Reads a line of user input. */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /** Prints welcome message. */
    public String showWelcome() {
        String logo = "\n____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Yoohoo \uD83D\uDE1B \uD83D\uDC2F" + logo + "\nis reporting for duty! \uD83E\uDDB8\u200D");
        showLine();
        showLine();
        return "Yoohoo \uD83D\uDE1B \uD83D\uDC2F" + "\nDuke reporting for duty! \uD83E\uDDB8\u200D";
    }

    /** Prints a breakLine. */
    public String showLine() {
        System.out.println("    ______________________________________________________");
        return "    ______________________________________________________";
    }

    /** Prints loading error.
     * @param error error message.
     */
    public String showLoadingError(String error) {
        System.out.println(error);
        return error;
    }

    /**
     * Prints duke exception error message.
     * @param error Duke exception message.
     */
    public String showError(String error) {
        System.out.println(error);
        return error;
    }

    /** Prints exit statement. */
    public String showExit() {
        System.out.println("    Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print task description.
     * @param counter Index of the task in the list.
     * @param task The task to print.
     */
    public String printTask(int counter, Task task) {
        System.out.println("    " + counter + ": " + task.toString());
        return counter + ": " + task.toString();
    }

    /**
     * Prints the search result.
     */
    public String printResult() {
        System.out.println("    Here are the matching tasks in your list:");
        return "Hehe found them \uD83D\uDE1B\n"
                + "Here are the matching tasks in your list:";
    }

    /**
     * Prints the add task message.
     * @param listSize The size of the task list.
     * @param task The task to add into the list.
     */
    public String addTask(int listSize, Task task) {
        System.out.println("    Got it. I've added this task: \n     " + task.toString());
        System.out.println("    Now you have " + listSize + " tasks in the list.");
        return "Got it. I've added this task: \n" + task.toString()
                + "\nNow you have " + listSize + " tasks in the list.";
    }

    /**
     * Prints the done message.
     * @param task The task that is completed.
     */
    public String markDone(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task.toString());
        return "Nice! I've marked this task as done:"
                + "\n" + task.toString();
    }

    /**
     * Prints the delete message.
     * @param listSize The size of the task list.
     * @param task The task to delete from the list.
     */
    public String markDelete(int listSize, Task task) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task.toString());
        System.out.println("    Now you have " + listSize + " tasks in the list.");
        return "Noted. I've removed this task:"
                + "\n" + task.toString()
                + "\nNow you have " + listSize + " tasks in the list.";
    }

    public String resultEmpty() {
        return "Hmm, it appears that your list is empty";
    }

}
