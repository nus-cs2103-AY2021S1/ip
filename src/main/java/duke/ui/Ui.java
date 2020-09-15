package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.model.task.Task;

/**
 * Deals with user interactions.
 */
public class Ui {
    static final String WELCOME = "Hello. I am Claude! What may I do for you today?";
    static final String GOODBYE = "Goodbye! Hope to see you again soon!";
    static final String LINE = "______________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads command input by user.
     *
     * @return String to be processed by parser.
     */
    public String readCommand() {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }

    /**
     * Prints a line that serves as a divider.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        return WELCOME;
    }

    /**
     * Prints goodbye message.
     */
    public String showGoodbye() {
        return (GOODBYE);
    }

    /**
     * Prints output from a Command.
     * @param s String to be printed.
     */
    public void showDetails(String s) {
        System.out.println(s);
    }

    /**
     * Prints a given ArrayList of Tasks.
     * @param taskList ArrayList of Tasks to be printed.
     */
    public String buildTaskList(ArrayList<Task> taskList) {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s = s = s + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return s;
    }

    /**
     * Prints error message from an Exception.
     * @param e Exception to be printed.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints error message from failing to load save file.
     */
    public void showLoadingError() {
        System.out.println("Failed to load from file. Initiating new instace.");
    }
}
