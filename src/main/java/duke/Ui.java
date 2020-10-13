package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    final private String LINE = "-------------------------------------";
    final private String LOADFAILMSG = "Sorry, I forgot you existed!";
    final private String ADDEDMSG = "Alright, I've added a new order: ";
    final private String DONEMSG = "Great choice! I have taken your order: ";
    final private String DELETEMSG = "Too bad. I'll remove the following order: ";
    final private String SAVEMSG = "Aright, I have remembered your sins :)";
    final private String RETLISTMSG = "Here's what you have ordered...";
    final private String CLEARMSG = "I have cleared your memory >:)";
    final private String DEFAULTERROR = DukeException.INVALID_COMMAND_EXCEPTION.toString();
    public final static String BYEMSG = "Bye! Please come again!";
    final private String NEWLINE = "\n";

    private Scanner s;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of input by user.
     */
    public String readCommand() {
        return s.nextLine();
    }

    /**
     * Returns welcome message.
     */
    public static String welcome() {
        return "Welcome to Hyu's drive-in!" + "\n" + "What would you like to have?" + "\n";
    }

    /**
     * Returns message after adding an item to TaskList.
     */
    public String addedItem(Task curr, int size) {
         return ADDEDMSG + NEWLINE + curr + NEWLINE + "You have ordered " + size + " items." + NEWLINE;
    }

    /**
     * Returns an error message.
     */
    public String showError(String ex) {
        return ex + NEWLINE;
    }

    /**
     * Returns message after marking an item in TaskList as done.
     */
    public String doneItem(Task curr) {
        return DONEMSG + NEWLINE + curr;
    }

    /**
     * Returns message after deleting an item in TaskList.
     */
    public String deleteItem(Task curr) {
        return DELETEMSG + NEWLINE + curr;
    }

    /**
     * Returns all items in TaskList.
     */
    public String returnList(ArrayList<Task> curr) {
        String temp = "";
        for (int k = 0; k < curr.size(); k++) {
            temp += ((k + 1) + ": " + curr.get(k)) + NEWLINE;
        }
        return RETLISTMSG + NEWLINE + temp;
    }

    /**
     * Returns message after manual saving.
     */
    public String save() {
        return SAVEMSG + NEWLINE;
    }

    /**
     * Returns message after clearing memory.
     */
    public String clear() {
        return CLEARMSG + NEWLINE;
    }

    /**
     * Returns message for default error.
     */
    public String DEFAULTERROR() {
        return DEFAULTERROR + NEWLINE;
    }

    /**
     * Returns bye message.
     */
    public String bye() {
        return BYEMSG + NEWLINE;
    }

    /**
     * Returns message when initialising save data fails.
     */
    public String showLoadingError() {
        return LOADFAILMSG + NEWLINE;
    }
}
