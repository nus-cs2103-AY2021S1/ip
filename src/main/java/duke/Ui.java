package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * Interacts with the user.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        printNicely("Hello! This is duckmoon99's Duke.",
                  "What can I do to help you today?");
    }

    public void bye() {
        printNicely("Bye. Hope to see you again soon!");
    }

    /**
     * List out the taskList the user has.
     *
     * @param taskList The user's taskList.
     */
    public void listOut(TaskList taskList) {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add(String.format("You currently have %d task(s)", taskList.size()));
        for (int i = 0; i < taskList.size(); i++) {
            toPrint.add(String.format("%d.%s", i+1, taskList.get(i)));
        }
        printNicelyCollection(toPrint);
    }

    /**
     * List out a taskList with a different message.
     *
     * @param message The message explaining the context of the list.
     * @param taskList The taskList.
     */
    public void listOut(String message, TaskList taskList) {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add(message);
        for (int i = 0; i < taskList.size(); i++) {
            toPrint.add(String.format("%d.%s", i+1, taskList.get(i)));
        }
        printNicelyCollection(toPrint);
    }

    /**
     * Prints nicely a collection of strings.
     *
     * @param strings The collection of strings to be printed.
     */
    public void printNicelyCollection(Collection<String> strings) {
        System.out.println("________________________________________");
        for (String s: strings) {
            System.out.println("    " + s);
        }
        System.out.println("________________________________________");
    }

    /**
     * Prints nicely all the strings as one.
     *
     * @param strings Vararg of strings to be printed.
     */
    public void printNicely(String ...strings) {
        printNicelyCollection(Arrays.asList(strings));
    }

    public String nextLine() {
        return sc.nextLine();
    }
}
