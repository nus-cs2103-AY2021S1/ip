package duke.utils;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

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
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }

    /**
     * Listens for user input and return the user input as string.
     *
     * @return a string that user has input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
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
    public void showRemovalMessage(Task removedTask, TaskList tasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.getNumberOfTask() + " tasks in the list.");
    }

    /**
     * Display the done message in the console.
     *
     * @param doneTask the task that has been done.
     */
    public void showDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
    }

    /**
     * Display the add message in the console.
     *
     * @param newTask the task that has been added
     * @param tasks the latest task list (after added the task above)
     */
    public void showAddMessage(Task newTask, TaskList tasks) {
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getNumberOfTask() + " tasks in the list.");
    }


    /**
     * Displays the list info in the console.
     *
     * @param tasks the latest task list
     */
    public void showListMessage(TaskList tasks) {
        tasks.printList();
    }

    /**
     * Displays a line
     */
    public void showLine(){
        System.out.println("-----------------------");
    }

    /**
     * Displays goodbye message
     */
    public void sayGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
