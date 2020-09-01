package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * User Interface class where interactions with user are handled
 */
public class Ui {
    private Scanner input;

    /**
     * Initializes the Ui object
     */
    public Ui() { }

    /**
     * Shows a line to dictate separation
     */
    public static String showLine() {
        return "_______________________________________________________ \n";
    }

    /**
     * Displays a welcome message
     *
     * @param list in which the tasks are stored
     */
    public String welcomeMessage(TaskList list) {
        String toReturn = showLine()
                + "Hello! Welcome to Duke, your personal task manager! \nWhat can I do for you?";
        if (list.getList().size() > 0) {
            toReturn = toReturn + "You have outstanding tasks. Type 'list' to view your current tasks.";
        }
        toReturn = toReturn + showLine();
        return toReturn;
    }

    /**
     * Displays a message to show that the task has been added
     */
    public String showAdded () {
        return "Okay! I've added it to the list."
                + " To view your current tasks, type 'list'";
    }

    /**
     * Displays the list of tasks stored.
     *
     * @param list in which tasks are stored
     */

    public String showList(ArrayList<Task> list) {
        String toReturn = showLine();
        if (list.size() == 0) {
            toReturn += "you do not have any tasks yet";
        } else {
            for (int i = 0; i < list.size(); i++) {
                int number = i + 1;
                toReturn += " " + number + "." + list.get(i) + "\n";
            }
        }
        toReturn += showLine();
        return toReturn;
    }

    /**
     * Displays a message to the user indicating that the command is invalid
     */
    public String showInvalidCommand() {
        return "I'm sorry I don't understand :(";
    }

    /**
     * Displays a message indicating that the program has come to an end.
     */
    public static String showEnd() {
        String toReturn = showLine() + " Bye. Hope to see you again soon!" + "\n" + showLine();
        return toReturn;
    }
}
