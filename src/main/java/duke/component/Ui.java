package duke.component;

import duke.task.Task;

import java.util.Scanner;
import java.util.function.Predicate;

public class Ui {
    private static final String HORIZONTAL_LINE = "\t=================================================================================";
    private final Scanner sc;

    /**
     * Creates a user-interface Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the given string.
     * @param str the string to print
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints the given message with the formatting of horizontal lines wrapping it.
     * @param message the message to be wrapped
     */
    public void output(String message) {
        print(HORIZONTAL_LINE + "\n\t  " + message + "\n" + HORIZONTAL_LINE + "\n");
    }

    /**
     * Prints the filtered list with nice horizontal line formatting wrapping it and a text tip.
     * @param list the list to be printed
     * @param predicate the condition for the list element to be printed
     * @param note the note that explains the filter, which should have a space at the end
     * @return the note with the size of the filtered list at the end
     */
    public String printList(TaskList list, Predicate<Task> predicate, String note) {
        System.out.println(HORIZONTAL_LINE + "\n\t  " + "Here are the tasks " + note + "in your list:");
        int n = list.print(predicate);
        System.out.println(HORIZONTAL_LINE + "\n");
        return note + n;
    }

    /**
     * Reads input from the user.
     * @return the user inputs in the next line
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Greets the user when the app is opened.
     */
    public void greeting() {
        output("Hello! I'm Duke\n\t  What can I do for you?");
    }

    /**
     * Says goodbye to the user when the app is exited.
     */
    public void close() {
        output("Bye. Hope to see you again soon!");
    }
}
