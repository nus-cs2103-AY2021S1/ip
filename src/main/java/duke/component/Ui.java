package duke.component;

import duke.task.Task;

import java.util.Scanner;
import java.util.function.Predicate;

public class Ui {
    private static final String HORIZONTAL_LINE = "\t=================================================================================";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void output(String message) {
        print(HORIZONTAL_LINE + "\n\t  " + message + "\n" + HORIZONTAL_LINE + "\n");
    }

    public String printList(TaskList list, Predicate<Task> predicate, String note) {
        System.out.println(HORIZONTAL_LINE + "\n\t  " + "Here are the tasks " + note + "in your list:");
        int n = list.print(predicate);
        System.out.println(HORIZONTAL_LINE + "\n");
        return note + n;
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void greeting() {
        output("Hello! I'm Duke\n\t  What can I do for you?");
    }

    public void close() {
        output("Bye. Hope to see you again soon!");
    }
}
