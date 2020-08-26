package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

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

    public void listOut(TaskList taskList) {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add(String.format("You currently have %d task(s)", taskList.size()));
        for (int i = 0; i < taskList.size(); i++) {
            toPrint.add(String.format("%d.%s", i+1, taskList.get(i)));
        }
        printNicelyCollection(toPrint);
    }

    public void listOut(String message, TaskList taskList) {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add(message);
        for (int i = 0; i < taskList.size(); i++) {
            toPrint.add(String.format("%d.%s", i+1, taskList.get(i)));
        }
        printNicelyCollection(toPrint);
    }

    public void printNicelyCollection(Collection<String> strings) {
        System.out.println("________________________________________");
        for (String s: strings) {
            System.out.println("    " + s);
        }
        System.out.println("________________________________________");
    }

    public void printNicely(String ...strings) {
        printNicelyCollection(Arrays.asList(strings));
    }

    public String nextLine() {
        return sc.nextLine();
    }
}
