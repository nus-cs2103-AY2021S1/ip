package duke.utils;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;



/**
 * A class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Display the welcome message in the console.
     */
    public void showWelcome() {
        String logo = "      _      _\n"
                + "| | / /| | / /\n"
                + "| |/ / | |/ /\n"
                + "|   <  |   <\n"
                + "|_|\\_\\ |_|\\_\\\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm KK\n"
                + " What can I do for you?");
    }

    /**
     * return a welcome message
     */
    public String welcomeMessge() {
        String logo = "      _      _\n"
                + "| | / /| | / /\n"
                + "| |/ / | |/ /\n"
                + "|   <  |   <\n"
                + "|_|\\_\\ |_|\\_\\\n";
        return ("Hello from\n" + logo
            + "Hello! I'm KK\n"
                + " What can I do for you?");
    }

    /**
     * Listens for user input and return the user input as string.
     *
     * @return a string that user has input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Display the error message in the console.
     *
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Display the removal message in the console.
     *
     * @param removedTask the task that has been removed
     * @param tasks the latest task list (after remove the task above)
     */
    public String showRemovalMessage(Task removedTask, TaskList tasks) {
        String message = "Noted. I've removed this task:\n";
        message = message + removedTask.toString() + "\n";
        message = message + "Now you have " + tasks.getNumberOfTask() + " tasks in the list.";

        System.out.println(message);
        return message;
    }

    /**
     * Display the done message in the console.
     *
     * @param doneTask the task that has been done.
     */
    public String showDoneMessage(Task doneTask) {
        String message = "Nice! I've marked this task as done:\n" + doneTask.toString();
        System.out.println(message);
        return message;
    }

    /**
     * Display the add message in the console.
     *
     * @param newTask the task that has been added
     * @param tasks the latest task list (after added the task above)
     */
    public String showAddMessage(Task newTask, TaskList tasks) {
        String message = newTask.toString() + "\n";
        message = message + "Now you have " + tasks.getNumberOfTask() + " tasks in the list.";
        System.out.println(message);
        return message;
    }


    /**
     * Displays the list info in the console.
     *
     * @param tasks the latest task list
     */
    public String showListMessage(TaskList tasks) {
        return tasks.printList();
    }

    /**
     * Displays the list info in the console.
     *
     * @param tasks a list of task list
     */
    public String showListMessage(List<Task> tasks) {
        String message = "";
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            message += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        System.out.println(message);
        return message;
    }

    public void showLine() {
        System.out.println("-----------------------");
    }

    /**
     * return helpMessage
     */
    public String helpMessage() {
        String message = "list\n"
                + "\n"
                + "event <content> /at <time> \n"
                + "\n"
                + "deadline <content> /by <time> \n"
                + "\n"
                + "todo <content>\n"
                + "\n"
                + "done <index>\n"
                + "\n"
                + "delete <index>\n"
                + "\n"
                + "bye\n";
        return message;
    }

    /**
     * Displays goodbye message
     */
    public void sayGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
