package duke.ui;

import duke.task.Task;
import duke.tool.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the UI manager for the system.
 */
public class Ui {

    /**
     * Message after clear command is executed
     */
    private static final String CLEAR_MESSAGE = "I have cleared all tasks!";

    /**
     * Welcome message
     */
    private static final String WELCOME_MESSAGE = "Hello, I am Duke! \n\t What can I do for you?";

    /**
     * Exit message
     */
    private static final String EXIT_MESSAGE = "Bye ! Hope to see you again soon.";

    /**
     * Done message
     */
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done: \n";

    /**
     * Horizontal Line
     */
    private static final String LINE = "-------------------------------------";

    /**
     * Shows the logo of Duke.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Prints the log of Duke.
     *
     * @param s Log content.
     */
    public void printLog(String s) {
        System.out.println(formatOut(s));
    }


    /**
     * Shows the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(formatOut(Ui.WELCOME_MESSAGE));
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println(formatOut(Ui.EXIT_MESSAGE));
    }

    /**
     * Show the notification after adding a new task.
     *
     * @param newTask  Task that is added to the list.
     * @param taskList Task list in the system.
     */
    public void showAddedNotification(Task newTask, TaskList taskList) {
        String notification = "Got it. I've added this task: " + "\n\t\t" +
                newTask.toString() + "\n\t" +
                String.format("Now you have %d tasks in the list.\n", taskList.getSize());

        System.out.print(formatOut(notification));
    }

    /**
     * Show the notification after an deletion happens.
     *
     * @param taskList    Task list in the system.
     * @param deletedTask Task that is deleted.
     */
    public void showDeletionNotification(TaskList taskList, Task deletedTask) {
        String notification = "Noted. I've removed this task: " + "\n\t\t" +
                deletedTask.toString() + "\n\t" +
                String.format("Now you have %d tasks in the list.\n", taskList.getSize());

        System.out.print(formatOut(notification));
    }

    /**
     * Print the task list in the system.
     *
     * @param tasks Task list in the system.
     */
    public void showTaskList(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list: \n\t");

        //Check whether there are any task in the list or not
        if (taskList.isEmpty()) {
            System.out.print(formatOut("You haven't added any task here !"));
            return;
        }

        //Produce output string
        for (Task task : taskList) {
            builder.append(taskList.indexOf(task) + 1).append(". ")
                    .append(task.toString()).append("\n").append("\t");
        }

        System.out.print(formatOut(builder.toString()));
    }

    /**
     * Returns the string of command from scanner.
     *
     * @return User input command.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Show the greeting after user mark a task as done.
     *
     * @param task The task that is marked as done.
     */
    public void showDoneGreet(Task task) {
        System.out.print(formatOut(Ui.DONE_MESSAGE + "\n\t" + task.toString()));
    }

    /**
     * Shows the message when user clear all data.
     */
    public void showClearMessage() {
        System.out.print(formatOut(Ui.CLEAR_MESSAGE + "\n\t"));
    }

    /**
     * Returns a formatted string that Duke interacts with users.
     *
     * @param s An input String.
     * @return A formatted string with built in format.
     */
    private String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n", Ui.LINE, s, Ui.LINE);
    }
}
