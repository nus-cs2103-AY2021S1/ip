package duke.io;

import duke.task.Task;

import java.util.ArrayList;

public class Layout {

    public void printLine() {
        String line = "\t";
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
        System.out.println("\tNice! I've marked this duke.task as done: ");
        System.out.println("\t\t" + task.toString());
        printLine();
    }
    
    public void printDeleted(Task task, int size) {
        String str = size > 1 ? "tasks" : "duke/task";
        printLine();
        System.out.println("\tNoted. I've removed this duke.task: ");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + size + " " + str + " in the list.");
        printLine();
    }

    public void printAddedMessage(String description, int size) {
        String str = size > 1 ? "tasks" : "duke/task";
        printLine();
        System.out.println("\tGot it. I've added this duke.task:");
        System.out.println("\t\t" + description);
        System.out.println("\tNow you have " + size + " " + str + " in the list.");
        printLine();
    }
    
    public void printCommands(String [] arr) {
        printLine();
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.println("\t" + arr[i] + "\n");
            } else {
                System.out.println("\t" + arr[i]);
            }
        }
        printLine();
    }

}
