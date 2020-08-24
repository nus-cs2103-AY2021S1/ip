package duke;

import java.util.LinkedList;
import java.util.Scanner;

import duke.task.Task;

/**
 * A class to handle the internal logic regarding interactions with the user.
 * This class handles the logic of receiving chat from the user and generating user interface.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Print out a list to the interface.
     * @param list the list of tasks to be printed out
     */
    public void printList(LinkedList<Task> list) {
        if (list.size() == 0) {
            System.out.println("     You have no tasks in your list now! " +
                    "Type todo, event or deadline to add some!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("     " + i + "." + list.get(i - 1));
            }
        }
    }

    /**
     * Print a specified line in the required format.
     * @param line the line of the string that needs to be printed
     */
    public void printLine(String line) {
        System.out.println("     " + line);
    }

    /**
     * Read next line from the user input.
     * @return String of the next user input line.
     */
    public String receiveChat() {
        return sc.nextLine();
    }

    /**
     * Show that Duke is fetching data from the local database.
     */
    public void showLoading() {
        System.out.println("     Data loading...");
    }

    /**
     * Show that Duke has fetched data from the local database.
     * @param num the number of tasks Duke fetched from the local database.
     */
    public void showLoaded(int num) {
        System.out.println("     Previous data found!\n     Now you have " + num
                + " task in the list!");
    }

    /**
     * Show that Duke did not find data from the local database and created a new data file.
     */
    public void showLoadedNew() {
        System.out.println("     Did not find any previous stored data and " +
                "new data file created!\n     Welcome!");
    }

    /**
     * Show that Duke encounters an error when accessing the file.
     */
    public void showLoadingError() {
        System.out.println("     Oops! Cannot access your data file and no " +
                "new data file has been created!");
    }

    /**
     * Print welcome to the user.
     */
    public void welcome() {
        printHorizontal();
        System.out.println("    " + " Hello! I'm Duke!\n     What can I do for you?");
        printHorizontal();
    }

    /**
     * Draw a horizontal line to divide different chats.
     */
    public void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }
}
