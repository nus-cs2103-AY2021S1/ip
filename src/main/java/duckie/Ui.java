package duckie;

import java.util.ArrayList;
import java.util.Scanner;

import duckie.task.Task;

/**
 * Deals with the interaction with users
 */
public class Ui {
    private final static String INDENT = "\t";
    private final static String HORIZ_LINE = INDENT +
            "____________________________________________________________";
    private final static String LOGO = INDENT
            + "           ____                   _      _\n"
            + INDENT + "    __    |  _ \\   _   _    ___  | | _  (_)  ___     __\n"
            + INDENT + "___( o)>  | | | | | | | | /  __| | |/ / | | / _ \\  <(o )___ \n"
            + INDENT + "\\ <_. )   | |_| | | | | | | (__  |   <  | | | __/   ( ._> /\n"
            + INDENT + " `---'    |____/  \\___,_|  \\ __| |_|\\_\\ |_| \\___|    `___' \n";
    private Scanner sc;

    /**
     * Instantiate an Ui object with the Scanner object ready
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the horizontal line required to break the lines
     *
     * @return the horizontal line String
     */
    public static String getHORIZ_LINE() {
        return HORIZ_LINE;
    }

    /**
     * Display the horizontal line to break the lines
     */
    public static void showLine() {
        System.out.println(HORIZ_LINE);
    }

    /**
     * Display Duckie introduction and the loading tasks message
     */
    public static void showIntro() {
        System.out.println(HORIZ_LINE + "\n" + LOGO + "\n" +
                INDENT + "Quack. Duckie is here to remember your tasks!" );
        showLoading();
    }

    /**
     * Display Duckie ending when 'bye' is input
     */
    public static void showEnding() {
        System.out.println(INDENT +
                "Quack! Hope to see you again!");
    }

    /**
     * Display the addTask message
     *
     * @param t1 Task that is added
     * @param tasks ArrayList containing all the tasks
     */
    public static void addTaskReply(Task t1, ArrayList<Task> tasks) {
        System.out.println(INDENT + "Quack! Added: " + t1);
        System.out.println(INDENT + "Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Display the contents in the List
     *
     * @param tasks ArrayList containing the tasks that are to be displayed
     */
    public static void displayListReply(ArrayList<Task> tasks) {
        int index = 1;
        System.out.println(INDENT + "Quack! You have these in your list currently: ");
        for (Task task : tasks) {
            System.out.println(INDENT + index + ". " + task);
            index++;
        }
    }

    /**
     * Display the contents in the List
     *
     * @param tasks ArrayList containing the tasks that are to be displayed
     */
    public static void displayMatchingTasksReply(ArrayList<Task> tasks) {
        int index = 1;
        System.out.println(INDENT + "Quack! Duckie found these matching tasks: ");
        for (Task task : tasks) {
            System.out.println(INDENT + index + ". " + task);
            index++;
        }
    }

    /**
     * Display the message that there are no tasks in the list
     */
    public static void displayNoListReply() {
        System.out.println(INDENT + "Quack. You have no tasks in the list currently");
        System.out.println(HORIZ_LINE);
    }

    /**
     * Display the current Task condition after being marked as Done
     * @param t1 Task being marked Done
     */
    public static void checkTaskReply(Task t1) {
        System.out.println(INDENT
                + "Quack! I've marked this task as done: \n"
                + INDENT + t1);
    }

    /**
     * Display the current Task that is being deleted
     * @param t1 Task being deleted
     */
    public static void deleteTaskReply(Task t1) {
        System.out.println(INDENT + "Quack! I've remove this task: \n" +
                INDENT + t1);
    }

    /**
     * Display the message that all tasks in the Tasklist has been removed.
     */
    public static void deleteAllReply() {
        System.out.println(INDENT + "Quack! All tasks are cleared in the list!");
    }

    /**
     * Display the Exception message
     * @param msg Message of the Exception thrown
     */
    public static void showError(String msg) {
        System.out.println(INDENT + "Oh no! " + msg);
    }

    /**
     * Read the input Command
     * @return Input string
     */
    public String readCommand() {
        String input = sc.nextLine();
        System.out.println(input);
        return input;
    }

    /**
     * Display the Loading Tasks message
     */
    public static void showLoading() {
        System.out.println(INDENT + "Loading tasks...");
        System.out.println(HORIZ_LINE);
    }
}
