package duke.util;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Handles the printing outputs according to the user commands.
 */
public class Ui {
    Scanner scanner;

    /**
     * Constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message when duke.Duke runs.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm duke.Duke baby :)\n" + "What can I do for you?");
    }

    /**
     * Reads the next line of the input.
     * @return String next line.
     */
    public String readNextLine() {
        String query = "";
        query = scanner.nextLine();
        return query;
    }

    /**
     * Prints a find message of the list of tasks that matches the find description.
     * @param taskList The list of tasks that matches the find description.
     */
    public void printFindMessage(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("I can't find anything that match!");
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            String stringCount = String.valueOf(i + 1);
            System.out.println(stringCount + ". " + taskList.get(i));
        }
    }

    /**
     * Prints a done message for the given Task.
     * @param doneTask Task that is marked as completed.
     */
    public void printDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + doneTask);
    }

    /**
     * Print an add message for the given Task.
     * @param newTask Task that has just been added.
     * @param taskList current TaskList.
     */
    public void printAddMessage(Task newTask, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");
    }

    /**
     * Prints a delete message for the deleted Task.
     * @param deletedTask Task that was deleted.
     * @param taskList current TaskList.
     */
    public void printDeleteMessage(Task deletedTask, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + deletedTask);
        System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
    }

    /**
     * Prints an exit message when duke.Duke exists.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message when file is not loaded.
     */
    public void showLoadingError() {
        System.out.println("Unable to load file. New file is created.");
    }

    /**
     * Prints an error message.
     * @param errorMessage error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
