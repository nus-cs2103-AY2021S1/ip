package ip.src.main.java;

import java.util.ArrayList;

public class Layout {
    private String line = "\t";

    public void printLine() {
        line = "\t";
        for (int i = 0; i < 50; i++) {
            line += "\u2500";
        }
        System.out.println(line);
    }

    public void print(String s) {
        printLine();
        System.out.println("\t" + s);
        printLine();
    }

    public void printTaskList(ArrayList<Task> arr) {
        printLine();
        if (arr.size() != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < arr.size(); i++) {
                Task task = arr.get(i);
                System.out.println("\t" + (i + 1) + "." + task.toString());
            }
        } else {
            System.out.println("\t No tasks");
        }
        printLine();
    }

    public void printMarkedDone(Task task) {
        printLine();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t\t" + task.toString());
        printLine();
    }

    public void printAddedMessage(String description, int size) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + description);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLine();
    }

}
