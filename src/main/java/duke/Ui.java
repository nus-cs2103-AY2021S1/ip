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

    public Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Reads the next line of input by user.
     *
     * @return the next command
     */
    public String readCommand() {
        return s.nextLine();
    }

    public static String welcome() {
        return "Welcome to Hyu's drive-in!" + "\n" + "What would you like to have?" + "\n";
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String addedItem(Task curr, int size) {
         return ADDEDMSG + NEWLINE + curr + NEWLINE + "You have ordered " + size + " items." + NEWLINE;
    }

    public String showError(String ex) {
        return ex + NEWLINE;
    }

    public String doneItem(Task curr) {
        return DONEMSG + NEWLINE + curr;
    }

    public String deleteItem(Task curr) {
        return DELETEMSG + NEWLINE + curr;
    }

    public String returnList(ArrayList<Task> curr) {
        String temp = "";
        for (int k = 0; k < curr.size(); k++) {
            temp += ((k + 1) + ": " + curr.get(k)) + NEWLINE;
        }
        return RETLISTMSG + NEWLINE + temp;
    }

    public String save() {
        return SAVEMSG + NEWLINE;
    }

    public String clear() {
        return CLEARMSG + NEWLINE;
    }

    public String DEFAULTERROR() {
        return DEFAULTERROR + NEWLINE;
    }

    public String bye() {
        return BYEMSG + NEWLINE;
    }

    public String showLoadingError() {
        return LOADFAILMSG + NEWLINE;
    }
}
