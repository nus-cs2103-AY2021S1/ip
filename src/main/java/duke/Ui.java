package duke;

import java.util.Scanner;

import exceptions.DukeException;
import tasks.TaskList;

/**
 * Handles interactions with users, namely accepting inputs and printing of info
 */
public class Ui {
    private Scanner sc;
    private final String ln = "----------------------------------------------------------------";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads an input from a user
     * @return The user's input
     */
    public String takeInput() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints a statement informing the user about the size of their TaskList currently.
     * @param size The size of the TaskList currently.
     */
    public void printListSize(int size) {
        System.out.println("You now have " + size + (size == 1
                ? " task in your list."
                : " tasks in your list."));
    }

    /**
     * Prints the TaskList for the user in a neat format.
     * @param tasks The TaskList to be printed.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are your tasks:");
        System.out.println(ln);
        System.out.println(tasks);
        System.out.println(ln);
    }
    public void printExitMessage() {
        System.out.println("Goodbye!");
    }
    public void printAddTask(String task) {
        System.out.println("Added new task: " + task);
    }
    public void printDoneTask() {
        System.out.println("Congrats, I've marked this task as finished!");
    }
    public void printDelTask(TaskList tasks, int idx) {
        System.out.println("The task " + tasks.getTasks().get(idx - 1) + " has been removed.");
    }

    /**
     * Prints the tasks matching the user's input keyword, or an alternative message if no tasks are found.
     * @param tasks The tasks to be printed, already converted to a String.
     * @param num The number of tasks in the TaskList.
     */
    public void printFindTask(String tasks, int num) {
        if (num == 0) {
            System.out.println("I couldn't find any tasks matching your keyword.");
        } else {
            System.out.println("I found " + num + (num > 1 ? " tasks " : " task ") + "matching your keyword.\n"
                    + tasks);
        }
    }
}
