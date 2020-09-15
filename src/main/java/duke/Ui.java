package duke;

import java.util.List;
import java.util.Scanner;

import duke.exception.InvalidIndexException;
import duke.task.Task;

/** Represents Ui of application that is responsible for interactions with user. */
public class Ui {

    protected Scanner scanner;

    /**
     * Constructs a new instance of a Ui object.
     */
    Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Generates and print welcome message when Duke is initialized.
     */
    String showWelcome() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Generates and print exit message when user exits Duke.
     */
    public String exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        return exitMessage;
    }

    /**
     * Prints all the tasks in current task list when user inputs "list" command.
     * @param tasks TaskList of current user.
     */
    public String listTasks(TaskList tasks) {
        if (tasks.size() <= 0) {
            String message = "Sorry, your task list is empty.";
            System.out.println(message);
            return message;
        }
        String message = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            String task = null;
            try {
                task = i + ". " + tasks.get(i - 1);
            } catch (InvalidIndexException e) {
                showError(e.getMessage());
            }
            message += task + "\n";
        }
        System.out.println(message);
        return message;
    }

    /**
     * Prints tasks matching find keyword after Find command is executed.
     * @param tasks Tasks that matches the Find command keyword.
     */
    public String printSearchResults(List<Task> tasks) {
        if (tasks.size() <= 0) {
            String message = "Sorry, there are no matching tasks with that keyword";
            System.out.println(message);
            return message;
        } else {
            String result = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= tasks.size(); i++) {
                String task = i + ". " + tasks.get(i - 1);
                result += task + "\n";
            }

            System.out.println(result);
            return result;
        }
    }

    /**
     * Prints message when user's command has been executed.
     * @param message Message to be print.
     */
    public String printReply(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Reads the input entered by user.
     * @return Returns user's input as String value.
     */
    String readCommand() {
        return scanner.nextLine();
    };

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading data");
    }

}
