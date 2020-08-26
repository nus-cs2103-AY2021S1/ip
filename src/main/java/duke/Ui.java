package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/** Represents Ui of application that is responsible for interactions with user. */
public class Ui {

    Scanner sc;

    /**
     * Constructs a new instance of a Ui object.
     */
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Generates and print welcome message when Duke is initialized.
     */
    void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Generates and print exit message when user exits Duke.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the tasks in current task list when user inputs "list" command.
     * @param tasks TaskList of current user.
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void printSearchResults(List<Task> tasks) {
        if (tasks.size() <= 0) {
            System.out.println("Sorry, there are no matching tasks with that keyword");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i - 1));
            }
        }
    }

    /**
     * Prints message when user's command has been executed.
     * @param message Message to be print.
     */
    public void printReply(String message) {
        System.out.println(message);
    }

    /**
     * Read the input entered by user.
     * @return Returns user's input as String value.
     */
    String readCommand() {
       return sc.nextLine();
    };

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading data");
    }
}
