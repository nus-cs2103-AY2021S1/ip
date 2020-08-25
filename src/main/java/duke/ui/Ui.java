package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Interacts with the user.
 */
public class Ui {
    private static String horizontalLine = "________________________________________";

    /**
     * Prints the welcome message
     */
    public static void showWelcome() {
        String logo = " ____        _" + System.lineSeparator()
                + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "| | | | | | | |/ / _ \\" + System.lineSeparator()
                + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
        String greeting = "Hello human, I'm Duke the supporting chatbot" + System.lineSeparator()
                + "What can I do for you?";
        System.out.println(logo);
        System.out.println(horizontalLine);
        System.out.println(greeting);
    }

    /**
     * Waits for the user to insert command and reads it.
     * @return A string represents the user's input
     */
    public static String requestCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println(horizontalLine);
        System.out.println("Your command:");
        String input = sc.nextLine();
        System.out.println(horizontalLine);
        return input;
    }

    /**
     * Shows the items in the task.
     * @param tasks The list of items to be shown
     */
    public static void showTasks(TaskList tasks) {
        String lazyHumanBash = "You have nothing in your list. Find something to do you human!";
        String showTasksMsg = "Here are the task(s) in your list:";
        if (tasks.size() == 0) {
            System.out.println(lazyHumanBash);
        } else {
            System.out.println(showTasksMsg);
            System.out.println(tasks);
        }
    }

    /**
     * Notifies the user that the file cannot be loaded.
     */
    public static void showFileLoadingError() {
        System.out.println("Data cannot be loaded");
    }

    /**
     * Prints out any error message that passed in 
     * @param errorMessage The error message to be printed 
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Says goodbye to the user.
     */
    public static void showGoodbye() {
        System.out.println("Bye human. See you again soon!");
    }

    /**
     * Prints out the number of item in the task.
     * @param tasks The task
     */
    public static void taskReport(TaskList tasks) {
        System.out.println("You have " + tasks.size() + " task(s) in your list");
    }

    /**
     * Notifies the user after successfully deleting a task.
     * @param task The deleted task
     * @param tasks The remaining task list
     */
    public static void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've deleted this task:");
        System.out.println(task);
        taskReport(tasks);
    }

    /**
     * Notifies the user after successfully marking a task as done.
     * @param task The task being marked as done
     */
    public static void showMarkAsDoneTask(Task task) {
        System.out.println("Good job bud! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Notifies the user whenever the system cannot understand the input command.
     */
    public static void showUnrecognizedCommandMessage() {
        System.out.println("I don't understand a single word you say, human!");
        System.out.println("Speak robot language pls");
    }

    /**
     * Notifies the user after successfully add a task into a task list.
     * @param task The added task
     * @param tasks The task list
     */
    public static void showAddSuccessful(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        taskReport(tasks);
    }

    /**
     * Show the user the result of a find command.
     * @param tasks The list of tasks that fulfill the requirement
     */
    public static void showFindResult(TaskList tasks) {
        System.out.println("Here is your search result: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }
    }
}
