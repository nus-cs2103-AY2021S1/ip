package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Makes a new Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads user input command.
     * @return user input command.
     */
    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * Prints a response to the user with 2 dividing lines.
     * @param print is the response to the user with 2 dividing lines.
     */
    public void printWithDivider(String print) {
        System.out.println("__________________________________________________");
        System.out.println(print);
        System.out.println("__________________________________________________");
    }

    /**
     * Prints a greeting to the user.
     */
    public void printWelcome() {
        printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints an exit message to the user.
     */
    public void printExit() {
        printWithDivider("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the tasks in the task list.
     * @param taskList is the list of tasks to be printed.
     */
    public void printList(TaskList taskList) {
        printWithDivider("Here are the tasks in your list:" + taskList);
    }

    /**
     * Prints a message noting that a task is done.
     * @param task is the task marked as done.
     */
    public void printDoneTask(Task task) {
        printWithDivider("Nice! I've marked this task as done:\n\t" + task);
    }

    /**
     * Prints a message noting that a task is deleted from the task list.
     * @param task is the task that was deleted.
     * @param taskList is the list of tasks in which the specified task was deleted from.
     */
    public void printDeleteTask(Task task, TaskList taskList) {
        printWithDivider("Noted. I've removed this task:\n\t" + task + getNumOfTasks(taskList));
    }

    /**
     * Prints a message noting that a task is added into the task list.
     * @param task is the task that was added.
     * @param taskList is the list in which the task was added to.
     */
    public void printAddTask(Task task, TaskList taskList) {
        printWithDivider("Got it. I've added this task:\n\t" + task + getNumOfTasks(taskList));
    }

    /**
     * Prints a numbered list of the tasks with the specified keyword.
     * @param keyword is the keyword used to find the tasks.
     * @param taskList is the task list to find the tasks with keyword from.
     */
    public void printFindTasks(String keyword, TaskList taskList) {
        printWithDivider("Here are the matching tasks in your list:" + taskList.getMatchingTasks(keyword));
    }

    /**
     * Gets the number of tasks in the task list.
     * @param taskList is the list to get the number of tasks from.
     * @return a message with the number of tasks in the list.
     */
    public String getNumOfTasks(TaskList taskList) {
        int numOfTasks = taskList.getNumOfTasks();
        String msg = "";
        if (numOfTasks == 1) {
            msg = String.format("\nNow you have %d task in the list.", numOfTasks);
        } else {
            msg = String.format("\nNow you have %d tasks in the list.", numOfTasks);
        }
        return msg;
    }

    /**
     * Prints an error message should there be an exception.
     * @param e is the exception in which we get the message from.
     */
    public void showError(Exception e) {
        printWithDivider(e.getMessage());
    }
}