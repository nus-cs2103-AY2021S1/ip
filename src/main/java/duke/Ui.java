package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Responsible for interactions with the user.
 */
public class Ui {

    /**
     * Scanner used for operations.
     */
    private Scanner s;

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
    public String exit() {
        String exitMessage = "Bye. Hope to see you soon!";
        System.out.println(exitMessage);
        return exitMessage;
    }

    /**
     * Prints a string.
     * @param s Message to be printed.
     */
    public String print(String s) {
        System.out.println(s);
        return s;
    }

    /**
     * Parses and prints the task list.
     * @param taskList TaskList to be printed.
     */
    public String list(TaskList taskList) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            String message = i + ".";
            message += task;
            s += message + "\n";
        }
        System.out.println(s);
        return s;
    }

    /**
     * Parses the list of tasks and prints the search result.
     * @param searchResult Search result for task list.
     */
    public String printSearchResult(List<Task> searchResult) {
        String s = "";
        if (searchResult.size() < 1) {
            s += "There are no matching results!\n";
        } else {
            s += "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= searchResult.size(); i++) {
                Task task = searchResult.get(i - 1);
                String message = i + ".";
                message += task;
                System.out.println(message);
                s += message + "\n";
            }
        }
        System.out.println(s);
        return s;
    }

    /**
     * Read the user input.
     */
    String readCommand() {
        return s.nextLine();
    }
}
