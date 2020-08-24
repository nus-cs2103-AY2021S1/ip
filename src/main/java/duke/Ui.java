package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Shows line divider
     */
    public void showLine() {
        System.out.println(
                "_____________________________________________________________");
    }

    /**
     * Shows welcome message and prints list of loaded tasks
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Beep Boop! Hello there!\n"
                + "What can I do for you?");
        showLine();
        System.out.println("Welcome back! Here are the tasks in your list:");
        for (Task task : Duke.getTasks().getListOfTasks()) {
            System.out.println(task);
        }
        showLine();
    }

    /**
     * Shows exit message
     */
    public void showGoodbye() {
        System.out.println("Goodbye, have a nice day :D");
        showLine();
    }

    /**
     * Shows loading error message
     */
    public void showLoadingError() {
        System.out.println("File not found");
    }

    /**
     * Takes in user input using scanner
     *
     * @return string of user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
