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
        return "______________________________________________\n";
    }

    /**
     * Displays a welcome message
     *
     */
    public static String showWelcomeMessage() {
        String toReturn = showLine()
                + "Hello! Welcome to Duke, your personal task manager! What can I do for you? \n\n";
        toReturn += "COMMANDS: \nTo see your list of tasks - list \n";
        toReturn += "To mark a task as done - done {task number} \n";
        toReturn += "To delete your tasks - delete {task number} \n";
        toReturn += "To find certain tasks - find {keyword} \n";
        toReturn += "To add a todo item to your list of tasks - todo {task} \n";
        toReturn += "To add an event item to your list of tasks - event {event details} /at {date/event} \n";
        toReturn += "To add a todo item to your list of tasks - deadline {deadline details} /by {date} \n";
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
        assert list.size() >= 0 : "list size should not be negative";
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
        return showLine() + " Bye. Hope to see you again soon!" + "\n" + showLine();
    }
}
