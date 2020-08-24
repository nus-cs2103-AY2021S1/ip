package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a new Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the input from the user.
     * @return The input from the user.
     */
    public String readInput() {
        return this.sc.nextLine().trim();
    }

    /**
     * Prints a response to the user.
     * @param response The response to be printed.
     */
    public void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printResponse("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints an exit message to the user.
     */
    public void printExit() {
        printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the tasks in the specified Task list.
     * @param taskList The task list that contains the list of tasks to be printed.
     */
    public void printList(TaskList taskList) {
        printResponse("Here are the tasks in your list:" + taskList);
    }

    /**
     * Prints a message to the user indicating that the specified task has been added to the
     * specified task list.
     * @param task The task that was added.
     * @param taskList The task list that the task was added to.
     */
    public void printAddTask(Task task, TaskList taskList) {
        printResponse("Got it. I've added this task: \n\t" + task + "\n" + getHowManyTasks(taskList));
    }

    /**
     * Prints a message to the user indicating that the specified task has been deleted from the
     * specified task list.
     * @param task The task that was deleted.
     * @param taskList The task list that the task was deleted from.
     */
    public void printDeleteTask(Task task, TaskList taskList) {
        printResponse("Noted. I've removed this task: \n\t" + task + "\n" + getHowManyTasks(taskList));
    }

    /**
     * Prints a message to the user indicating that the specified task has been marked done.
     * @param task The task that was marked done.
     */
    public void printDoneTask(Task task) {
        printResponse("Nice! I've marked this task as done:\n\t" + task);
    }

    /**
     * Gets the number of tasks in the specified task list.
     * @param taskList The task list to get the number of tasks from.
     * @return A string indicating the number of tasks in the specified task list.
     */
    public String getHowManyTasks(TaskList taskList) {
        int numberOfTasks = taskList.getNumberOfTasks();
        String output = "";
        if (numberOfTasks == 1) {
            output = String.format("Now you have %d task in the list.", numberOfTasks);
        } else {
            output = String.format("Now you have %d tasks in the list.", numberOfTasks);
        }
        return output;
    }

    /**
     * Prints the tasks that occur on the specified date.
     * @param taskList The task list to get the tasks from.
     * @param localDate The date that you want to get the tasks for.
     */
    public void printViewTasks(TaskList taskList, LocalDate localDate) {
        TaskList taskListOnDate = taskList.getTaskListOnDate(localDate);
        printResponse("Here are the tasks on this date:" + taskListOnDate);
    }

    /**
     * Prints the tasks that contain the specified keyword.
     * @param taskList The task list to find the tasks in.
     * @param keyword The string that the tasks you are finding should contain.
     */
    public void printFindTasks(TaskList taskList, String keyword) {
        TaskList tasksWithKeyword = taskList.getTasksWithKeyword(keyword);
        printResponse("Here are the matching tasks in your list:" + tasksWithKeyword);
    }

    /**
     * Prints the error message of the exception.
     * @param e The exception to get the message from.
     */
    public void showError(Exception e) {
        printResponse(e.getMessage());
    }
}
