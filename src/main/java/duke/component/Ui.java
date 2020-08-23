package duke.component;

import duke.task.Task;

import java.util.Scanner;
import java.util.function.Predicate;

public class Ui {
    private static final String horizontalLine = "\t=================================================================================";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void output(String message) {
        print(horizontalLine + "\n\t  " + message + "\n" + horizontalLine + "\n");
    }

    public String printList(TaskList list, Predicate<Task> predicate, String note) {
        System.out.println(horizontalLine + "\n\t  " + "Here are the tasks " + note + "in your list:");
        int n = list.print(predicate);
        System.out.println(horizontalLine + "\n");
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
