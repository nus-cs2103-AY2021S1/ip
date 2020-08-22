package duke;

import java.util.List;
import java.util.Scanner;
import duke.task.*;

/**
 * Responsible for interactions with the user.
 */
public class Ui {

    /**
     * Scanner used for operations.
     */
    Scanner s;

    /**
     * Creates a new instance of a Ui object.
     */
    Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Prints a greeting message.
     */
    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints an exit message.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    /**
     * Prints a string.
     * @param s Message to be printed.
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Parses and prints the task list.
     * @param taskList TaskList to be printed.
     */
    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            String message = i + ".";
            message += task;
            System.out.println(message);
        }
    }

    /**
     * Parses the list of tasks and prints the search result.
     * @param searchResult Search result for task list.
     */
    public void printSearchResult(List<Task> searchResult) {
        if(searchResult.size() < 1) {
            System.out.println("There are no matching results!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= searchResult.size(); i++) {
                Task task = searchResult.get(i - 1);
                String message = i + ".";
                message += task;
                System.out.println(message);
            }
        }
    }

    /**
     * Read the user input.
     */
    String readCommand() {
        return s.nextLine();
    }
}
