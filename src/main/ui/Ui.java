package main.ui;

import java.util.Scanner;

import main.task.Task;
import main.task.TaskList;

/**
 * Represents the ui of duke.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class Ui {
    private static final String SEPARATOR = "    ____________________________________"
            + "________________________\n";
    private static final String ERROR_MESSAGE = String.format("     ☹ OOPS!!! "
            + "Something went wrong!\n%s", SEPARATOR);

    /**
     * Gets the input from the user.
     * @param sc the scanner used for the
     * @return
     */
    public String[] getInput(Scanner sc) {
        System.out.println();
        String[] input = sc.nextLine().trim().split(" ", 2);
        printSeparator();

        return input;
    }

    /**
     * Prints the exit message.
     */
    public void printExit() {
        System.out.printf("     Bye. Hope to see you again soon!\n%s",
                SEPARATOR);
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        System.out.printf("%s     Hello! I'm Duke\n"
                + "     What can I do for you?\n%s", SEPARATOR, SEPARATOR);
    }

    /**
     * Prints the task list.
     * @param tasks the task list.
     */
    public void printTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i));
        if (tasks.size() == 0)
            System.out.println("     There are no tasks yet!");
        System.out.print(SEPARATOR);
    }

    /**
     * Prints when a task has been successfully added.
     * @param task the task added.
     * @param taskNum the number of tasks in the list.
     */
    public void printAddSuccess(Task task, int taskNum) {
        boolean isSingular = taskNum == 1;
        System.out.printf("     Got it. I've added this task:\n"
                        + "       %s\n"
                        + "     Now you have %d %s in the list.\n%s",
                task, taskNum, isSingular ? "task" : "tasks", SEPARATOR);
    }

    /**
     * Prints when the task has been successfully marked as done.
     * @param task the task marked as done.
     */
    public void printDoneSuccess(Task task) {
        System.out.printf("     Nice! I've marked this task as done:\n"
                        + "       %s\n%s", task, SEPARATOR);
    }

    /**
     * Prints when the task has been removed successfully.
     * @param removed the task removed.
     * @param taskNum the number of tasks in the list.
     */
    public void printRemoveSuccess(Task removed, int taskNum) {
        boolean isSingular = taskNum == 1;
        System.out.printf("     Noted. I've removed this task:\n       %s\n"
                        + "     Now you have %d %s in the list.\n%s",
                removed, taskNum, isSingular ? "task" : "tasks", SEPARATOR);
    }

    /**
     * Prints the default error message.
     */
    public void printError() {
        System.out.print(ERROR_MESSAGE);
    }

    /**
     * Prints the message of the exception thrown.
     * @param e the exception thrown.
     */
    public void printExceptionMessage(Exception e) {
        System.out.printf("%s\n%s", e.getMessage(), SEPARATOR);
    }

    /**
     * Prints the separator.
     */
    public void printSeparator() {
        System.out.printf("%s", SEPARATOR);
    }
}
