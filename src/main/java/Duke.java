package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            String command = sc.next();
            switch(command) {
                case "bye":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("    ____________________________________________________________\n");
                    return;
                case "list":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    for (Task task: taskList) {
                        task.printDescription();
                    }
                    System.out.println("    ____________________________________________________________\n");
                    break;
                case "done":
                    int index = sc.nextInt();
                    Task currentTask = taskList.get(index - 1);
                    currentTask.markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done: ");
                    currentTask.printDescription();
                    System.out.println("    ____________________________________________________________\n");
                    break;
                default:
                    String event = sc.nextLine();
                    taskList.add(new Task(command + event));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     added: " + command + event);
                    System.out.println("    ____________________________________________________________\n");
                    break;
            }
        }
    }
}
